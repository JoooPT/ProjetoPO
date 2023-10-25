package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public abstract class IntervalFunction extends Function {
    private Range _range;
    
    public IntervalFunction(String name, Range range){
        super(name);
        _range = range;
        update();
    }

    @Override
    public String toString(){
        return "" + super.value() + "=" + super.getName() + "(" + _range + ")";
    }

    public Range getRange(){
        return _range;
    }
}
