package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public abstract class IntervalFunction extends Function {
    private Range _range;
    
    public IntervalFunction(String name, Range range){
        super(name);
        _range = range;
    }

    @Override
    public String toString(){
        String value = null;
        try{
            value = "" + super.value();  
        } catch(UnsupportedArgument e){
            value = "#VALUE";
        }

        return "" + value + "=" + super.getName() + "(" + _range + ")";
    }

    public Range getRange(){
        return _range;
    }
}
