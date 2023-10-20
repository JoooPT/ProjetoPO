package xxl.core;

import java.util.List;

import xxl.core.exception.UnsupportedArgument;

public class Product extends IntervalFunction {
    
    public Product(String name, Range range){
        super(name,range);
    }

    protected Literal compute(){
        List<Cell> list = getRange().getCells();
        int res = 1;
        for (Cell c: list) {
            try {
                res *= c.value().asInt();
            }
            catch(UnsupportedArgument e){
                return new LiteralString("#VALUE");
            }
        }
        return new LiteralInteger(res);
    }
}
