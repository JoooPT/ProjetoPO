package xxl.core;

import java.util.List;

import xxl.core.exception.UnsupportedArgument;

public class Coalesce extends IntervalFunction{
    
    public Coalesce(String name, Range range){
        super(name,range);
    }

    protected Literal compute(){
        List<Cell> list = getRange().getCells();
        String res;
        for (Cell c: list) {
            try {
                res = c.value().asString();
                return new LiteralString(res);
            } catch(UnsupportedArgument e){}
        }
        return new LiteralString("");
    }
}
