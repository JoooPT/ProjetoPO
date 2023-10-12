package xxl.core;

import java.io.Serializable;

public abstract class CellStructure implements Serializable{
    
    abstract Cell getCell(int row, int column);
    abstract boolean addCell(Cell cell);
}
