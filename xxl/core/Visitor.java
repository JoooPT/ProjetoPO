package xxl.core;

public interface Visitor {
    public void visit(Content c);
    public void visit(NulContent c);
    public void visit(Literal l);
    public void visit(LiteralString l);
    public void visit(LiteralInteger l);
    public void visit(LiteralInvalid l);
    public void visit(Reference ref);
    public void visit(Function func);
    public void visit(BinaryFunction func);
    public void visit(IntervalFunction func);
    public void visit(Add func);
    public void visit(Mul func);
    public void visit(Div func);
    public void visit(Sub func);
    public void visit(Coalesce func);
    public void visit(Concat func);
    public void visit(Average func);
    public void visit(Product func);
}
