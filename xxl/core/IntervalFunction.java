package xxl.core;

public abstract class IntervalFunction extends Function {
    private Range _range;
    
    public IntervalFunction(String name, Range range){
        super(name);
        _range = range;
        update();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString(){
        return "" + super.value() + "=" + super.getName() + "(" + _range + ")";
    }

    public Range getRange(){
        return _range;
    }
}
