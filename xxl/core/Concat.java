package xxl.core;

import java.util.List;

import xxl.core.exception.UnsupportedArgument;

public class Concat extends IntervalFunction {
    
    public Concat(String name, Range range){
        super(name,range);
    }

    protected Literal compute(){
        List<Cell> list = getRange().getCells();
        String res = "";
        String str;
        for (Cell c: list) {
            try {
                str = c.value().asString();
                str = str.substring(1);
                res += str;
            } catch(UnsupportedArgument e){}
        }
        return new LiteralString(res);
    }

    public Content copy(){
        return new Concat(getName(), getRange());
    }
}
