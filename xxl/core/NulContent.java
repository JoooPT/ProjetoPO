package xxl.core;

public class NulContent extends Content {
    private static NulContent _nulContent = new NulContent();
    private NulContent() {};
    public static NulContent getNulContent() {
        return _nulContent;
    } 
}
