package xxl.core;

public class Add extends BinaryFunction {
    
    public Add(String name, Content arg1, Content arg2){
        super(name,arg1,arg2);
    }

    protected Literal compute(){
        int res = super.getArg1().asInt() + super.getArg2().asInt();
        return new LiteralInteger(res);
    }
}
