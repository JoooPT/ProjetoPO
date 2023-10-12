package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public abstract class Function extends Content {
    private String _name;

    /**
     * Constructor
     * @param name of the function.
     */
    public Function(String name){
        _name = name;
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

    protected abstract Literal compute() throws UnsupportedArgument;

    /**
     * @returns the result of the function as a literal.
     * @throws UnsupportedArgument
     */
    public Literal value() throws UnsupportedArgument{
        return compute();
    }
}
