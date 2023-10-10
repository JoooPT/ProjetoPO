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

    
    @Override
    public String toString(){
        return "=" + _row + ";" + _column;   
    }

    /**
     * 
     * @return the value of the reference cell
     */
    Literal value(){
        return _spreadsheet.getCell(_row, _column).value();
    }
}
