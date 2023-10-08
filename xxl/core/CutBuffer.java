package xxl.core;

import java.util.List;
import java.util.ArrayList;

public class CutBuffer {
    
    private List<Cell> _cells;

    public CutBuffer() {  /* é preciso? */

    }

    public List<Cell> getCells() {
        return _cells;
    }

    public void setCells(List<Cell> cells) {
        _cells = cells;
    }

}
