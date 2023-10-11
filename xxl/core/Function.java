package xxl.core;

import java.nio.channels.UnsupportedAddressTypeException;

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

    public String getName(){
        return _name;
    }

    public String asString() throws UnsupportedArgument {
        return value().asString();
    }

    public int asInt() throws UnsupportedArgument{
        return value().asInt();
    }

    protected abstract Literal compute() throws UnsupportedArgument;

    public Literal value() throws UnsupportedArgument{
        return compute();
    }
}
