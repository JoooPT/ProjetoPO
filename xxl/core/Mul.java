package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public class Mul extends BinaryFunction {
    
    public Mul(String name, Content arg1, Content arg2){
        super(name,arg1,arg2);
    }

    protected Literal compute() throws UnsupportedArgument{
        int res = super.getArg1().asInt() * super.getArg2().asInt();
        return new LiteralInteger(res);
    }
}
