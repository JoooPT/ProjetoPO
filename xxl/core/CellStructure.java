package xxl.core;

public abstract class CellStructure {
    
    abstract Cell getCell(int row, int column);
    abstract boolean addCell(Cell cell);
}
