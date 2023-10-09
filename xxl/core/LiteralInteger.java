package xxl.core;

public class LiteralInteger extends Literal {
    private int _value;

    /**
     * Constructor
     * @param value (integer) of the Literal
     */
    public LiteralInteger(int value){
        _value = value;
    }

    @Override
    public String toString(){
        return "" + _value;
    }

    /**
     * @return the value as a String if it is a String otherwise throws Exception
     */
    public String asString(){ //throws  
        return ""; //throw new 
    }

    /**
     * @return the value of the Literal as a integer
     */
    public int asInt(){
        return _value;
    }
}
