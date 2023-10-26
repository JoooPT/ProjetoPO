package xxl.core;

import xxl.core.exception.NoNameException;

public class FunctionComparator extends CellComparator {
    public int compare(Cell c1, Cell c2) {
        int i = 0;
        try {
            i = c1.getContent().getName().compareTo(c2.getContent().getName());
            if (i == 0) return super.compare(c1, c2);
        } catch (NoNameException e) {
            return super.compare(c1, c2);
        }
        return i;
         
    }
}
