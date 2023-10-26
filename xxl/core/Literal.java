package xxl.core;

public abstract class Literal extends Content{
    
    /* Constructor */
    Literal value(){
        return this;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
