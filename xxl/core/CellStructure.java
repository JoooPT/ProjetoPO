package xxl.core;

import java.io.Serializable;

import java.util.List;

public abstract class CellStructure implements Serializable{
    
    abstract Cell getCell(int row, int column);
    abstract boolean addCell(Cell cell);
    abstract boolean removeCell(Cell cell);
    abstract List<Cell> getCells();
}
