package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public abstract class Content {
    
    public abstract String toString();
    abstract Literal value() throws UnsupportedArgument;
    public String asString() throws UnsupportedArgument {
        return value().asString();
    }
    public int asInt() throws UnsupportedArgument {
        return value().asInt();
    }


}
