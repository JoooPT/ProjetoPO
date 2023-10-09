package xxl.core;

public class LiteralString extends Literal{
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
     * @return the value as a integer if it is a integer otherwise throws Exception
     */
    public int asInt(){ //throws
        return 0; //throw new 
    }

    /**
     * 
     * @return the value of the Literal
     */
    public String asString(){
        return _value;
    }
}

