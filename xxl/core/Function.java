package xxl.core;

public abstract class Function extends Content {
    private String _name;

    /**
     * Constructor
     * @param name of the function.
     */
    public Function(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }

    protected abstract Literal compute();

    public Literal value(){
        return compute();
    }
}
