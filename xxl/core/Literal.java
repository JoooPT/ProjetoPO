package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public abstract class Literal extends Content{
    
    Literal value() throws UnsupportedArgument{
        return this;
    }
}
