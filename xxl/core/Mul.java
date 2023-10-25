package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public class Mul extends BinaryFunction {
    
    /* Constructor */
    public Mul(String name, Content arg1, Content arg2){
        super(name,arg1,arg2);
    }

    /**
     * @returns arg1 * arg2 as a Literal.
     */
    protected void compute(){
        Literal res;
        try{
            res = new LiteralInteger(super.getArg1().asInt() * super.getArg2().asInt());
        }
        catch( UnsupportedArgument e){
            res = LiteralInvalid.getLiteralInvalid();
        }
        super.setValue(res);
    }

    public Content copy(){
        return new Mul(getName(), getArg1(), getArg2());
    }
}
