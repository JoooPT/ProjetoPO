package xxl.core;

import java.util.List;

import xxl.core.exception.UnsupportedArgument;

public class Average extends IntervalFunction{
    
    public Average(String name, Range range){
        super(name,range);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    protected void compute(){
        List<Cell> list = getRange().getCells();
        int res = 0;
        Literal result;
        try {
            for (Cell c: list) { 
                res += c.value().asInt();
            }
            result = new LiteralInteger(res / list.size());
        }catch(UnsupportedArgument e){
            result = LiteralInvalid.getLiteralInvalid();
        }
        super.setValue(result);
    }

    public Content copy(){
        return new Average(getName(), getRange());
    }
}
