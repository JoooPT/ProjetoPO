package xxl.core;

import java.util.Map;
import java.util.HashMap;

public class MapCells extends CellStructure {
    private Map<String,Cell> _cells;

    /* Constructor */
    public MapCells(){
        _cells = new HashMap<String,Cell>();
    }
    
    /**
     * @param row
     * @param column
     * @returns the cell with the specified coordinates from the map. Creates a new one if it doesn't exist.  
     */
    Cell getCell(int row, int column) {
        if (_cells.containsKey(key(row,column))) return _cells.get(key(row,column));
        else return new Cell(row, column);
    }

    /**
     * @param
     * @returns true if the cell has been added successfully.
     */
    boolean addCell(Cell cell){
        String key = key(cell.getRow(), cell.getCol());
        if(!_cells.containsKey(key)){
            _cells.put(key, cell);
            return true;
        }
        else return false;
    }

    /**
     * 
     * @param row
     * @param column
     * @returns the key in the format  "row|column".
     */
    public String key(int row, int column){
        return "" + row + "|" + column;
    }
}
