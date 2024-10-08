package xxl.core;

public class Reference extends Content {
    private int _row;
    private int  _column;
    private Spreadsheet _spreadsheet;

    /**
     * Constructor
     * 
     * @param row of the referenced Cell
     * @param column of the referenced Cell
     * @param spreadsheet where the reference is located
     */
    public Reference(int row, int column, Spreadsheet spreadsheet){
        _row = row;
        _column = column;
        _spreadsheet = spreadsheet;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * @returns the value of the referenced cell as a String.
     */
    @Override
    public String toString(){        
        return "" + value() + "=" + _row + ";" + _column;   
    }

    /**
     * @returns the value of the reference cell
     */
    Literal value(){
        return _spreadsheet.getCell(_row, _column).value();
    }

    public Cell getCell(){
        return _spreadsheet.getCell(_row, _column);
    }

    public Content copy(){
        return new Reference(_row, _column, _spreadsheet);
    }
}
