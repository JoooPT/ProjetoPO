package xxl.core;


import java.io.Serial;
import java.io.Serializable;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import xxl.core.exception.InvalidRangeException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;

  private int _rows;
  private int _columns;
  private boolean _changed;
  private CellStructure _cells;
  private CutBuffer _cutBuffer;
  private Set<User> _users;
  private String _filename;
  
  /* Constructor */
  public Spreadsheet(int rows, int columns) {
    _rows = rows;
    _columns = columns;
    _changed = true;
    _cells = new MapCells();
    _users = new HashSet<User>();
    _cutBuffer = new CutBuffer();
  }

  /**
   * 
   * @param filename
   */
  public void setFileName(String filename){
    _filename = filename;
  }

  /**
   * 
   * @returns the name of the file associated with the spreadsheet.
   */
  public String getFilename(){
    return _filename;
  }

  /**
   * 
   * @return true if file has no name, false otherwise.
   */
  public boolean noFilename(){
    return _filename == null;
  }

  /**
   * 
   * @param status
   */
  public void setChangedStatus(Boolean status){
    _changed = status;
  }
  
  /**
   * 
   * @returns true if the spreadsheet has been changed since the last save.
   */
  public boolean changed(){
    return _changed;
  }

  /**
   * 
   * @param row
   * @param column
   * @returns the cell with the specified coordinates.
   */
  public Cell getCell(int row, int column){
    return _cells.getCell(row, column);
  }

  /**
   * 
   * @returns the cells in the cut buffer as a list.
   */
  public List<Cell> getCutBuffer() {
    return _cutBuffer.getBuffer();
  }

  /**
   * Creates a new range
   * 
   * @param range in the format "firstRow;firstColumn:lastRow;lastColumn"
   * @returns the range that is created
   * @throws InvalidRangeException
   */
  public Range createRange(String range) throws InvalidRangeException {
    String[] rangeCoordinates;
    int firstRow, firstColumn, lastRow, lastColumn;
    
    if (range.indexOf(':') != -1) {
      rangeCoordinates = range.split("[:;]");
      firstRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = Integer.parseInt(rangeCoordinates[1]);
      lastRow = Integer.parseInt(rangeCoordinates[2]);
      lastColumn = Integer.parseInt(rangeCoordinates[3]);
    } else {
      rangeCoordinates = range.split(";");
      firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
    }

    if (firstRow != lastRow && firstColumn != lastColumn){
      throw new InvalidRangeException(range);
    }
    else if(firstRow < 1 || lastRow > _rows || firstColumn < 1 || lastColumn > _columns){
      throw new InvalidRangeException(range);
    }
    return new Range(firstRow, lastRow, firstColumn, lastColumn, this);
  }

  /** 
   * Copies a range of cells to the cut buffer.
   * 
   * @param range in the format "firstRow;firstColumn:lastRow;lastColumn"
   * @throws InvalidRangeException
   */
  public void copy(String range) throws InvalidRangeException {
    Range rangeObj = createRange(range);
    _cutBuffer.setBuffer(rangeObj.getCells(), rangeObj.getBeginRow(), rangeObj.getBeginColumn());
  }

  /**
   * Clears a range of cells, removing their content
   * 
   * @param range in the format "firstRow;firstColumn:lastRow;lastColumn"
   * @throws InvalidRangeException
   */
  public void clear(String range) throws InvalidRangeException {
    Range rangeObj = createRange(range);
    List<Cell> list = rangeObj.getCells();
    for (Cell c: list) {
      c.setContent(NulContent.getNulContent());
    }
    setChangedStatus(true);
  }

  /**
   * 
   * @param user
   */
  public void addUser(User user) {
    _users.add(user);
  }

  /**
   * Insert specified content in specified address.
   *
   * @param row of the cell to change. 
   * @param column of the cell to change
   * @param contentSpecification in a string format to put in the specified cell.
   * @throws UnrecognizedEntryException
   */
  public void insertContent(int row, int column, Content content) throws UnrecognizedEntryException {
    Cell cell = getCell(row, column);
    cell.setContent(content);
    _cells.addCell(cell);
    setChangedStatus(true);
  }
}
