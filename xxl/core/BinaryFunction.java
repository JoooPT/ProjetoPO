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
        String arg1 = "" + _arg1;
        String arg2 = "" + _arg2;
        if(arg1.contains("=")){
            arg1 = (_arg1.toString().split("="))[1];
        }
        if(arg2.contains("=")){
            arg2 = (_arg2.toString().split("="))[1];
        }

        String args =  "(" + arg1 + "," + arg2 + ")";
        return value + name + args;
    }

    public Content getArg1(){
        return _arg1;
    }
    public Content getArg2(){
        return _arg2;
    }
}
