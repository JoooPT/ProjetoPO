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

    public String asString(){
        return value().asString();
    }

    public int asInt(){
        return value().asInt();
    }

    protected abstract Literal compute();

    public Literal value(){
        return compute();
    }
}
