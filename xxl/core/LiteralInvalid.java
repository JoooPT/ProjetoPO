package xxl.core;

import xxl.core.exception.UnsupportedArgument;

public class LiteralInvalid extends Literal {
    private static LiteralInvalid _literalInvalid = new LiteralInvalid();
    private LiteralInvalid(){};
    public static LiteralInvalid getLiteralInvalid() {
        return _literalInvalid;
    } 

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString(){
        return "#VALUE";
    }

    public Literal value() {
        return LiteralInvalid.getLiteralInvalid();
    }

    public Content copy(){
        return LiteralInvalid.getLiteralInvalid();
    }

    /**
     * @returns the value as a String if it is a String, otherwise throws Exception.
     * @throws UnsupportedArgument
     */
    public String asString() throws UnsupportedArgument{ 
        throw new UnsupportedArgument(); 
    }

     /**
     * @returns the value as a integer if it is a integer otherwise throws Exception
     */
    public int asInt() throws UnsupportedArgument{
        throw new UnsupportedArgument();
    }

}
