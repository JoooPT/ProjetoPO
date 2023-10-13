package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public class NulContent extends Content {
    private static NulContent _nulContent = new NulContent();
    private NulContent() {};
    public static NulContent getNulContent() {
        return _nulContent;
    } 

    @Override
    public String toString(){
        return "";
    }

    public Literal value() throws UnsupportedArgument {
        throw new UnsupportedArgument();
    }
}
