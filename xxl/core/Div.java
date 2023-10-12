package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public class Div extends BinaryFunction {
    
    /* Constructor */
    public Div(String name, Content arg1, Content arg2){
        super(name,arg1,arg2);
    }

    /**
     * @returns arg1 / arg2 as a Literal.
     * @throws UnsupportedArgument 
     */
    protected Literal compute() throws UnsupportedArgument{
        int res = super.getArg1().asInt() / super.getArg2().asInt();
        return new LiteralInteger(res);
    }
}
