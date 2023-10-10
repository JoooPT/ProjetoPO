package xxl.core;

public class Coalesce extends IntervalFunction{
    
    public Coalesce(String name, Range range){
        super(name,range);
    }

    protected Literal compute(){
        return new LiteralString("Not implemented yet");
    }
}
