package xxl.core.exception;

public class InvalidRangeException extends Exception {
    private String _range;

    public InvalidRangeException(String range){
        _range = range;
    }

    public String getInvalidRange(){
        return _range;
    }
}
