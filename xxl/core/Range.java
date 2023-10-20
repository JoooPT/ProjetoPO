package xxl.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Range {
    
    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;
    private Spreadsheet _spreadsheet;

    /* Constructor */
    public Range(int beginRow, int endRow, int beginColumn, int endColumn, Spreadsheet spreadsheet) {
        _beginRow = beginRow;
        _beginColumn = beginColumn;
        _endRow = endRow;
        _endColumn = endColumn;
        _spreadsheet = spreadsheet;
    }

    /**
     * 
     * @returns an umnodifiable list with the cells in the range.
     */
    public List<Cell> getCells() {
        List<Cell> list = new ArrayList<Cell>();
        if (_beginRow == _endRow) {
            for (int col = _beginColumn; col <= _endColumn; col++) {
                list.add(_spreadsheet.getCell(_beginRow, col));
            }
        }
        else if(_beginColumn == _endColumn){
            for (int row = _beginRow; row <= _endRow; row++) {
                list.add(_spreadsheet.getCell(row, _beginColumn));
            }
        }         
        return Collections.unmodifiableList(list);
    }

    // Needs a toString for IntervalFunction.toString()
    public String toString(String str) {
        if (_beginRow == _endRow && _beginColumn == _endColumn) {
            return "" + _beginRow + ";" + _beginColumn;
        }
        return "" + _beginRow + ";" + _beginColumn + ":" + _endRow + ";" + _endColumn;
    }
}
