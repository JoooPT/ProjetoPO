package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public class Add extends BinaryFunction {
    
    /* Constructor */
    public Add(String name, Content arg1, Content arg2){
        super(name,arg1,arg2);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * @returns arg1 + arg2 as a Literal.
     */
    protected void compute(){
        Literal res;
        try{
            res = new LiteralInteger(getArg1().asInt() + getArg2().asInt());
        }
        catch( UnsupportedArgument e){
            res = LiteralInvalid.getLiteralInvalid();
        }
        super.setValue(res);
    }

    public Content copy(){
        return new Add(getName(), getArg1(), getArg2());
    }
}
