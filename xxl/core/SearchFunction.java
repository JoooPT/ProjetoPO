package xxl.core;

public class SearchFunction implements Visitor {
    private String _name;
    private boolean _found;

    public SearchFunction(String name) {
        _name = name;
        _found = false;
    }

    public boolean wasFound() {
        return _found;
    }

    public void reset() {
        _found = false;
    }

    public void visit(Content c) {}
    public void visit(NulContent c) {}
    public void visit(Literal l) {}
    public void visit(LiteralString l) {}
    public void visit(LiteralInteger l) {}
    public void visit(LiteralInvalid l) {}
    public void visit(Reference ref) {}
    
    public void visit(BinaryFunction func) {}
    public void visit(IntervalFunction func) {}
    public void visit(Function func) {}
    
    public void visit(Add func) {
        if ((func.getName()).contains(_name)) {
            _found = true;
        }
    }
    
    public void visit(Mul func) {
        if ((func.getName()).contains(_name)) {
            _found = true;
        }
    }
    public void visit(Div func) {
        if ((func.getName()).contains(_name)) {
            _found = true;
        }
    }
    public void visit(Sub func) {
        if ((func.getName()).contains(_name)) {
            _found = true;
        }
    }
    public void visit(Coalesce func) {
        if ((func.getName()).contains(_name)) {
            _found = true;
        }
    }
    public void visit(Concat func) {
        if ((func.getName()).contains(_name)) {
            _found = true;
        }
    }
    public void visit(Average func) {
        if ((func.getName()).contains(_name)) {
            _found = true;
        }
    }
    public void visit(Product func) {
        if ((func.getName()).contains(_name)) {
            _found = true;
        }
    }
}
