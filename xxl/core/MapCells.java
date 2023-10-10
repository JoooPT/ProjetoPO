package xxl.core;

import java.util.Map;
import java.util.HashMap;

public class MapCells extends CellStructure {
    private Map<String,Cell> _cells;

    public MapCells(){
        _cells = new HashMap<String,Cell>();
    }
    
    Cell getCell(int row, int column) {
        if (_cells.containsKey(key(row,column))) return _cells.get(key(row,column));
        else return new Cell(row, column);
    }

    boolean addCell(Cell cell){
        String key = key(cell.getRow(), cell.getCol());
        if(!_cells.containsKey(key)){
            _cells.put(key, cell);
            return true;
        }
        else return false;
    }

    public String key(int row, int column){
        return "" + row + "|" + column;
    }
}
