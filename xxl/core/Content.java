package xxl.core;

import java.io.Serializable;

import xxl.core.exception.NoNameException;
import xxl.core.exception.UnsupportedArgument;

public abstract class Content implements Serializable{
    private boolean _linkedContent = true;

    public void accept(Visitor v) {
        v.visit(this);
    }

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

    public String getName() throws NoNameException {
        throw new NoNameException();
    }

    public void setLinked(boolean flag){
        _linkedContent = flag;
    }


}
