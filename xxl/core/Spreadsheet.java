package xxl.core;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import xxl.core.exception.InvalidRangeException;
import xxl.core.exception.OutofBoundsException;
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
  
  public Spreadsheet(int rows, int columns) {
    _rows = rows;
    _columns = columns;
    _changed = true;
    _cells = new MapCells();
    _users = new HashSet<User>();
  }

  // FIXME define methods

  public void setFileName(String filename){
    _filename = filename;
  }

  public String getFilename(){
    return _filename;
  }

  public void setChangedStatus(Boolean status){
    _changed = status;
  }
  
  public boolean getChangedStatus(){
    return _changed;
  }

  public Cell getCell(int row, int column){
    return _cells.getCell(row, column);
  }

  /**
   * 
   * @returns the cells in the cut buffer as a list
   */
  public List<Cell> getCutBuffer() {
    return _cutBuffer.getCells();
  }

  public Range createRange(String range) throws InvalidRangeException, OutofBoundsException {
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
      throw new InvalidRangeException();
    }
    else if(firstRow < 1 || lastRow > _rows || firstColumn < 1 || lastColumn > _columns){
      throw new OutofBoundsException();
    }
    return new Range(firstRow, lastRow, firstColumn, lastColumn, this);
  }

  public void copy(String range) {
    Range rangeObj = createRange(range);
    _cutBuffer.setCells(rangeObj.getCells());
  }

  public void clear(String range) {
    Range rangeObj = createRange(range);
    List<Cell> list = rangeObj.getCells();
    for (Cell c: list) {
      c.setContent(NulContent.getNulContent());
    }
    setChangedStatus(true);
  }

  public void addUser(User user) {
    _users.add(user);
  }

  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, Content content) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    Cell cell = getCell(row, column);
    cell.setContent(content);
    setChangedStatus(true);
  }
}
