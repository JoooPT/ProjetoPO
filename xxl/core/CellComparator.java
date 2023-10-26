package xxl.core;
import java.util.Comparator;

public class CellComparator implements Comparator<Cell> {
    public int compare(Cell c1, Cell c2) {
        if (c1.getRow() > c2.getRow()) return 1;
        else if (c1.getRow() < c2.getRow()) return -1;
        else return (c1.getCol() - c2.getCol());
    }
}
