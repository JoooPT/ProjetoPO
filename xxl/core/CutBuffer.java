package xxl.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class CutBuffer implements Serializable{
    private List<Cell> _buffer;

    public CutBuffer(){
        _buffer = new ArrayList<Cell>();
    }

    /**
     * @returns the cells in the cut buffer.
     */
    public List<Cell> getBuffer() {
        return _buffer;
    }

    public void clearBuffer() {
        _buffer.clear();
    }

    /**
     * @param cells copied to the cut buffer.
     */
    public void setBuffer(List<Cell> cells, int rowBegin, int columnBegin) {
        for(Cell c: cells){
            _buffer.add(new Cell(c.getRow()- rowBegin,c.getCol() - columnBegin, c.getCopyContent()));
        }
    }

}
