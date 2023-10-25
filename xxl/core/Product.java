package xxl.core;

import java.util.List;

import xxl.core.exception.UnsupportedArgument;

public class Product extends IntervalFunction {
    
    public Product(String name, Range range){
        super(name,range);
    }

    protected void compute(){
        List<Cell> list = getRange().getCells();
        int res = 1;
        Literal result;
        try{
            for (Cell c: list) {
                res *= c.value().asInt();
            }
            result = new LiteralInteger(res);
        }catch(UnsupportedArgument e){
            result = LiteralInvalid.getLiteralInvalid();
        }
        setValue(result);
    }

    public Content copy(){
        return new Product(getName(), getRange());
    }
}
