package xxl.core;


import java.io.Serial;
import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import xxl.core.exception.InvalidRangeException;
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
  private List<User> _users = new ArrayList<User>();
  private String _filename;
  
  /* Constructor */
  public Spreadsheet(int rows, int columns) {
    _rows = rows;
    _columns = columns;
    _changed = true;
    _cells = new MapCells();
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
    resetCutBuffer();
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
      c.getContent().setLinked(false);
      _cells.removeCell(c);
    }
    setChangedStatus(true);
  }

  public void paste(String range) throws InvalidRangeException{
    Range rangeObj = createRange(range);
    List<Cell> cells = rangeObj.getCells();
    List<Cell> buffer = getCutBuffer();
    int size = cells.size();

    if(size == 1){
      for(Cell cell: buffer){
        int rowPaste = cell.getRow() + cells.get(0).getRow();
        int colPaste = cell.getCol() + cells.get(0).getCol();
        if(rowPaste>_rows || colPaste > _columns){
          break;
        }
          insertContent(cell.getRow() + cells.get(0).getRow(), cell.getCol() + cells.get(0).getCol(), cell.getContent());
        }
    }
    else if(size == buffer.size()){
      for(int i = 0; i<size; i++){
        cells.get(i).setContent(buffer.get(i).getContent());
        _cells.addCell(cells.get(i));
      }
    }
    setChangedStatus(true);
  }

  public List<Cell> searchValue(String value) {
    List<Cell> cells = _cells.getCells();
    Iterator<Cell> iter = cells.iterator();
    while (iter.hasNext()) {
      if (!value.equals(iter.next().value().toString())) {
        iter.remove();
      }
    }
    CellComparator comparator = new CellComparator();
    cells.sort(comparator);
    return cells;
  }

  public List<Cell> searchFunction(String name) {
    List<Cell> cells = _cells.getCells();
    Iterator<Cell> iter = cells.iterator();
    SearchFunction visitor = new SearchFunction(name);
    while (iter.hasNext()) {
      iter.next().getContent().accept(visitor);
      if (!visitor.wasFound()) iter.remove();
      visitor.reset();
    }
    CellComparator comparator = new FunctionComparator();
    cells.sort(comparator);
    return cells;
  }

  /**
   * 
   * @param user
   */
  public void addUser(User user) {
    _users.add(user);
  }
  
  public List<User> getUsers() {
    return _users;
  }

  /**
   * Insert specified content in specified address.
   *
   * @param row of the cell to change. 
   * @param column of the cell to change
   * @param contentSpecification in a string format to put in the specified cell.
   */
  public void insertContent(int row, int column, Content content) {
    Cell cell = getCell(row, column);
    cell.getContent().setLinked(false);
    cell.setContent(content);
    _cells.addCell(cell);
    setChangedStatus(true);
  }

  /**
     * @returns the correct represention of each cell on the cut buffer
     */
    public String visualizeCellBuffer(Cell cell){
      return "" + (cell.getRow() + 1) + ";" + (cell.getCol() + 1) + "|" + cell.getContent().toString();
  }

  public void resetCutBuffer() {
    _cutBuffer.clearBuffer();
  }

  public void addCell(Cell cell){
    _cells.addCell(cell);
  }
  
}
