package xxl.core;

public class Product extends IntervalFunction {
    
    public Product(String name, Range range){
        super(name,range);
    }

    protected Literal compute(){
        return new LiteralString("Not implemented yet");
    }
}
