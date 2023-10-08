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
  private Map<String, Cell> _cells;
  private CutBuffer _cutBuffer;
  private Set<User> _users;
  
  public Spreadsheet(int rows, int columns) {
    _rows = rows;
    _columns = columns;
    _changed = false;
    _cells = new HashMap<String, Cell>();
    _users = new HashSet<User>();
  }

  // FIXME define methods
  
  /**
   * 
   * @returns the cells in the cut buffer as a list
   */
  public List<Cell> getCutBuffer() {
    return _cutBuffer.getCells();
  }

  public Range createRange(String range) throws ? {
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

    // check if coordinates are valid
    // if yes
    return new Range(firstRow, lastRow, firstColumn, lastColumn, this);
  }

  public void copy(String range) {
    Range rangeObj = createRange(range);
    _cutBuffer.setCells(rangeObj.getCells());
  }

  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    //FIXME implement method
  }
}
