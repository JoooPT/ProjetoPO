package xxl.core;

import java.io.Serializable;

import xxl.core.exception.UnsupportedArgument;

public abstract class Content implements Serializable{
    private boolean _linkedContent = true;

    public abstract String toString();
    
    abstract Literal value();
    
    public String asString() throws UnsupportedArgument {
        return value().asString();
    }
    
    public int asInt() throws UnsupportedArgument {
        return value().asInt();
    }
    
    public abstract Content copy();

    public boolean isLinked(){
        return _linkedContent;
    }

    public void setLinked(boolean flag){
        _linkedContent = flag;
    }


}
