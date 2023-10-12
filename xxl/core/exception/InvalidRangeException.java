package xxl.core.exception;

/* thrown when a range with invalid dimensions is created */
public class InvalidRangeException extends Exception {
    private String _range;

    /**
     * @param range in the format "firstRow;firstColumn:lastRow;lastColumn"
     */
    public InvalidRangeException(String range){
        _range = range;
    }

    public String getInvalidRange(){
        return _range;
    }
}
