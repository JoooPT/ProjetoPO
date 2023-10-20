package xxl.core;

import java.io.Serializable;
import java.util.List;

public class CutBuffer implements Serializable{
    private List<Content> _contentBuffer;
    private String _direction;

    public CutBuffer(){
        _contentBuffer = new ArrayList<Content>();
    }

    /**
     * @returns the cells in the cut buffer.
     */
    public List<Cell> getBuffer() {
        return _contentBuffer;
    }

    /**
     * @param cells copied to the cut buffer.
     */
    public void setBuffer(List<Cell> cells) {
        for(Cell c: cells){
            _contentBuffer.add(c.getCopyContent());
        }
    }

}
