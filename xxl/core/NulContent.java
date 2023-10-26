package xxl.core;

public class NulContent extends Content {
    private static NulContent _nulContent = new NulContent();
    private NulContent() {};
    public static NulContent getNulContent() {
        return _nulContent;
    } 

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString(){
        return "";
    }

    public Literal value(){
        return LiteralInvalid.getLiteralInvalid();
    }

    public Content copy(){
        return NulContent.getNulContent();
    }
}
