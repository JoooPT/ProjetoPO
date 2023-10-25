package xxl.core;

import java.util.List;

import xxl.core.exception.UnsupportedArgument;

public class Coalesce extends IntervalFunction{
    
    public Coalesce(String name, Range range){
        super(name,range);
    }

    protected void compute(){
        List<Cell> list = getRange().getCells();
        String res = "";
        for (Cell c: list) {
            try {
                res = c.value().asString();
                super.setValue(new LiteralString(res));
                return;
            } catch(UnsupportedArgument e){
                res = "";
            }
        }
        super.setValue(new LiteralString(res));
    }

    public Content copy(){
        return new Coalesce(getName(), getRange());
    }
}
