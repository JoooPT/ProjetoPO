package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public class Sub extends BinaryFunction{

    /* Constructor */
    public Sub(String name, Content arg1, Content arg2){
        super(name,arg1,arg2);
    }

    /**
     * @returns arg1 - arg2 as a Literal.
     */
    protected void compute(){
        Literal res;
        try{
            res = new LiteralInteger(getArg1().asInt() - getArg2().asInt());
        }
        catch( UnsupportedArgument e){
            res = LiteralInvalid.getLiteralInvalid();
        }
        setValue(res);
    }

    public Content copy(){
        return new Sub(getName(), getArg1(), getArg2());
    }
}
