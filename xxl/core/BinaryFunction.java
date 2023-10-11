package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public abstract class BinaryFunction extends Function {
    private Content _arg1;
    private Content _arg2;

    public BinaryFunction(String name, Content arg1, Content arg2){
        super(name);
        _arg1 = arg1;
        _arg2 = arg2;
    }

    @Override
    public String toString(){
        String value = null;
        try{
            value = "" + super.value();  
        } catch(UnsupportedArgument e){
            value = "#VALUE";
        }
        String name = "=" + super.getName();
        String args =  "(" + _arg1.toString() + "," + _arg2.toString() + ")";
        args = args.replace("=","");
        return value + name + args;
    }

    public Content getArg1(){
        return _arg1;
    }
    public Content getArg2(){
        return _arg2;
    }
}
