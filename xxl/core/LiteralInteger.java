package xxl.core;

import xxl.core.exception.UnsupportedArgument;

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
     * @returns the value as a String if it is a String, otherwise throws Exception.
     * @throws UnsupportedArgument
     */
    public String asString() throws UnsupportedArgument{ 
        throw new UnsupportedArgument(); 
    }

    /**
     * @returns the value of the Literal as an integer.
     */
    public int asInt(){
        return _value;
    }
}
