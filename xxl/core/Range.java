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
        String key;
        Map<String, Cell> map = _spreadsheet.getCells();
        List<Cell> list = new ArrayList<Cell>();
        if (_beginRow == _endRow) {
            for (int col = _beginColumn; col < _endColumn; col++) {
                key = "" + _beginRow + "|" + col;
                if (map.containsKey(key)) {
                    list.add(map.get(key));
                }
                else {
                    Cell cell = new Cell(_beginRow, col);
                }
            }
        }

        return list;
    }
}
