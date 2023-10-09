package xxl.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Range {
    
    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;
    private Spreadsheet _spreadsheet;

    public Range(int beginRow, int endRow, int beginColumn, int endColumn, Spreadsheet spreadsheet) {
        _beginRow = beginRow;
        _beginColumn = beginColumn;
        _endRow = endRow;
        _endColumn = endColumn;
        _spreadsheet = spreadsheet;
    }

    List<Cell> getCells() {
        List<Cell> list = new ArrayList<Cell>();
        if (_beginRow == _endRow) {
            for (int col = _beginColumn; col < _endColumn; col++) {
                list.add(_spreadsheet.getCell(_beginRow, col));
            }
        }
        else for (int row = _beginRow; row < _endRow; row++) {
                list.add(_spreadsheet.getCell(row, _beginColumn));
            }

        return list;
    }
}
