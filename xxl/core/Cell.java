package xxl.core;

public class Cell {
    
    private int _row;
    private int _column;
    private Content _content;

    /** 
     * 
     */
    public Cell(int row, int column) {
        _row = row;
        _column = column;
    }

    /**
     * 
     * @returns the coordinates of the cell as a String
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

    }

    /**
     * 
     * @returns the content of the cell as a literal
     */
    Literal value() {

    }
}
