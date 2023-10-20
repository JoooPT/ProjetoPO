package xxl.core;

import xxl.core.exception.UnsupportedArgument;

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

    /**
     * @throws UnsupportedArgument
     * @returns the value of the referenced cell as a String.
     */
    @Override
    public String toString(){
        String value = null;
        try{
            value = "" + value();  
        } catch(UnsupportedArgument e){
            value = "#VALUE";
        }
        
        return value + "=" + _row + ";" + _column;   
    }

    /**
     * @throws UnsupportedArgument
     * @returns the value of the reference cell
     */
    Literal value() throws UnsupportedArgument{
        return _spreadsheet.getCell(_row, _column).value();
    }

    public Content copy(){
        return new Reference(_row, _column, _spreadsheet);
    }
}
