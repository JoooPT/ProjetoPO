package xxl.core;

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
        return "" + super.value() + "=" + super.getName() + "(" + _arg1.toString() + "," + _arg2.toString() + ")";
    }

    public Content getArg1(){
        return _arg1;
    }
    public Content getArg2(){
        return _arg2;
    }
}
