package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public class LiteralString extends Literal {
    private String _value;

     /**
     * Constructor
     * @param value (String) of the Literal
     */
    public LiteralString(String value){
        _value = value;
    }

    @Override
    public String toString(){
        return _value;
    }

    /**
     * @returns the value as a integer if it is a integer otherwise throws Exception
     */
    public int asInt() throws UnsupportedArgument{
        throw new UnsupportedArgument();
    }

    /**
     * 
     * @return the value of the Literal without '
     */
    public String asString(){
        String string = _value.replace("\'", "");
        return string;
    }

    public Content copy(){
        return new LiteralString(_value);
    }
}

