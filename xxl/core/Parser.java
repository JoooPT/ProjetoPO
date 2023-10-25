package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.InvalidRangeException;

import java.util.List;

public class Parser {

  private Spreadsheet _spreadsheet;
  
  Parser() {
  }

  Parser(Spreadsheet spreadsheet) {
    _spreadsheet = spreadsheet;
  }

  Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException, InvalidRangeException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      parseDimensions(reader);

      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }

    return _spreadsheet;
  }

  private void parseDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException {
    int rows = -1;
    int columns = -1;
    
    for (int i = 0; i < 2; i++) {
      String[] dimension = reader.readLine().split("=");
      if (dimension[0].equals("linhas"))
        rows = Integer.parseInt(dimension[1]);
      else if (dimension[0].equals("colunas"))
        columns = Integer.parseInt(dimension[1]);
      else
        throw new UnrecognizedEntryException(dimension[0]);
    }

    if (rows <= 0 || columns <= 0)
      throw new UnrecognizedEntryException("Dimensões inválidas para a folha");

    _spreadsheet = new Spreadsheet(rows, columns);
  }

  public void parseLine(String line) throws UnrecognizedEntryException, InvalidRangeException  {
    String[] components = line.split("\\|");

    if (components.length == 1) // do nothing
      return;
    
    if (components.length == 2) {
      String[] address = components[0].split(";");
      Content content = parseContent(components[1]);
      _spreadsheet.insertContent(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
    } else
      throw new UnrecognizedEntryException("Wrong format in line: " + line);
  }

  // parse the begining of an expression
  Content parseContent(String contentSpecification)throws UnrecognizedEntryException, InvalidRangeException  {
    char c = contentSpecification.charAt(0);

    if (c == '=')
      return parseContentExpression(contentSpecification.substring(1));
    else
      return parseLiteral(contentSpecification);
  }

  private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
    if (literalExpression.charAt(0) == '\'')
      return new LiteralString(literalExpression);
    else {
      try {
        int val = Integer.parseInt(literalExpression);
        return new LiteralInteger(val);
      } catch (NumberFormatException nfe) {
        throw new UnrecognizedEntryException("Número inválido: " + literalExpression);
      }
    }
  }

  // contentSpecification is what comes after '='
  private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException, InvalidRangeException  {
    if (contentSpecification.contains("("))
      return parseFunction(contentSpecification);
    // It is a reference
    String[] address = contentSpecification.split(";");
    return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]), _spreadsheet);
  }

  private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException, InvalidRangeException {
    String[] components = functionSpecification.split("[()]");
    if (components[1].contains(","))
      return parseBinaryFunction(components[0], components[1]);
        
    return parseIntervalFunction(components[0], components[1]);
  }

  private BinaryFunction parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException{
    String[] arguments = args.split(",");
    Content arg0 = parseArgumentExpression(arguments[0]);
    Content arg1 = parseArgumentExpression(arguments[1]);
    BinaryFunction func;
    switch (functionName) {
      case "ADD" -> func = new Add(functionName, arg0, arg1);
      case "SUB" -> func = new Sub(functionName, arg0, arg1);
      case "MUL" -> func = new Mul(functionName, arg0, arg1);
      case "DIV" -> func = new Div(functionName, arg0, arg1);
      default -> throw new UnrecognizedEntryException("Função inválida:"+ functionName);
    };
    if (arg0.toString().contains(";")  && arg0.toString().charAt(0) != '\'') {
      ((Reference) arg0).getCell().addObserver(func);
    }
    if (arg1.toString().contains(";")  && arg1.toString().charAt(0) != '\'') {
      ((Reference) arg1).getCell().addObserver(func);
    }
    return func;
    
  }

  private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
    if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
      String[] address = argExpression.split(";");
      return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]),_spreadsheet);
      // pode ser diferente do anterior em parseContentExpression
    } else
      return parseLiteral(argExpression);
  }

  private IntervalFunction parseIntervalFunction(String functionName, String rangeDescription)
    throws UnrecognizedEntryException, InvalidRangeException {
    Range range = _spreadsheet.createRange(rangeDescription);
    IntervalFunction func;
    List<Cell> cells = range.getCells();
    switch (functionName) {
      case "CONCAT" -> func = new Concat(functionName, range);
      case "COALESCE" -> func = new Coalesce(functionName, range);
      case "PRODUCT" -> func = new Product(functionName, range);
      case "AVERAGE" -> func = new Average(functionName, range);
      default -> throw new UnrecognizedEntryException(rangeDescription);
    };
    for (Cell c: cells) {
      c.addObserver(func);
    }
    return func;
    
  }
  
}
