package xxl.core;

public class Concat extends IntervalFunction {
    
    public Concat(String name, Range range){
        super(name,range);
    }

    protected Literal compute(){
        return new LiteralString("Not implemented yet");
    }
}
