package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public abstract class Function extends Content implements Observer {
    private String _name;
    private Literal _value;

    /**
     * Constructor
     * @param name of the function.
     */
    public Function(String name){
        _name = name;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * @returns the name of the function.
     */
    public String getName(){
        return _name;
    }

    /**
     * @returns the value of the function as a string.
     */
    public String asString() throws UnsupportedArgument {
        return value().asString();
    }

    /**
     * @returns the value of the function as an integer.
     */
    public int asInt() throws UnsupportedArgument{
        return value().asInt();
    }

    protected abstract void compute();
    
    public void update(){
        compute();
    }

    /**
     * @returns the result of the function as a literal.
     */
    public Literal value(){
        if(_value == null){
            update();
        }
        return _value;
    }

    public void setValue(Literal value){
        _value = value;
    }
}
