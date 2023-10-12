package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public abstract class Literal extends Content{
    
    /* Constructor */
    Literal value() throws UnsupportedArgument{
        return this;
    }
}
