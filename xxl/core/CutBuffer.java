package xxl.core;

import java.io.Serializable;
import java.util.List;

public class CutBuffer implements Serializable{
    private List<Cell> _cells;
    private String _direction;

    /**
     * @returns the cells in the cut buffer.
     */
    public List<Cell> getCells() {
        return _cells;
    }

    /**
     * @param cells copied to the cut buffer.
     */
    public void setCells(List<Cell> cells) {
        _cells = cells;
    }

}
