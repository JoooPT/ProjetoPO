package xxl.core;

import java.io.Serializable;

import xxl.core.exception.UnsupportedArgument;

public class LiteralInteger extends Literal implements Serializable {
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
    public String asString() throws UnsupportedArgument{ 
        throw new UnsupportedArgument(); 
    }

    /**
     * @return the value of the Literal as a integer
     */
    public int asInt(){
        return _value;
    }
}
