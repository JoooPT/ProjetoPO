package xxl.core;

import java.util.List;

import xxl.core.exception.UnsupportedArgument;

public class Average extends IntervalFunction{
    
    public Average(String name, Range range){
        super(name,range);
    }

    protected Literal compute(){
        List<Cell> list = getRange().getCells();
        int res = 0;
        for (Cell c: list) {
            try {
                res += c.value().asInt();
            }
            catch(UnsupportedArgument e){
                return new LiteralString("#VALUE");
            }
        }
        return new LiteralInteger(res % list.size());
    }

    public Content copy(){
        return new Average(getName(), getRange());
    }
}
