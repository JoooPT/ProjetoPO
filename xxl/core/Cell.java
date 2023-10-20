package xxl.core;

import java.io.Serializable;

import xxl.core.exception.UnsupportedArgument;

public class Cell implements Serializable{
    
    private int _row;
    private int _column;
    private Content _content;

    /* Constructor */
    public Cell(int row, int column) {
        _row = row;
        _column = column;
        _content = NulContent.getNulContent();
    }

    /**
     * 
     * @returns the row of the cell.
     */
    public int getRow(){
        return _row;
    }

    /**
     * 
     * @returns the column of the cell.
     */
    public int getCol(){
        return _column;
    }

    /**
     * 
     * @returns the coordinates of the cell as a String.
     */
    @Override
    public String toString() {
        return "" + _row + ";" + _column + "|" + _content.toString();
    }

    /**
     * 
     * @param content that is inserted in the cell
     */
    void setContent(Content content) {
        _content = content;
    }

    Content getCopyContent(){
        return _content.copy();
    }

    /**
     * 
     * @returns the content of the cell as a literal
     */
    Literal value() throws UnsupportedArgument {
        return _content.value();
    }
}
