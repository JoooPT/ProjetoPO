package xxl.core;

import java.io.Serializable;

import xxl.core.exception.UnsupportedArgument;

import java.util.Set;
import java.util.HashSet;

public class Cell implements Serializable{
    
    private int _row;
    private int _column;
    private Content _content;
    private Set<Observer> _observers = new HashSet<>();

    /* Constructor */
    public Cell(int row, int column) {
        _row = row;
        _column = column;
        _content = NulContent.getNulContent();
    }
    public Cell(int row, int column, Content content) {
        _row = row;
        _column = column;
        _content = content;
    }

    /**
     * 
     * @returns the row of the cell.
     */
    public int getRow(){
        return _row;
    }

    /**
     * 
     * @returns the column of the cell.
     */
    public int getCol(){
        return _column;
    }

    /**
     * 
     * @returns the coordinates of the cell as a String.
     */
    @Override
    public String toString() {
        return "" + _row + ";" + _column + "|" + _content.toString();
    }

    /**
     * 
     * @param content that is inserted in the cell
     */
    void setContent(Content content) {
        _content = content;
    }

    Content getCopyContent(){
        return _content.copy();
    }

    /**
     * 
     * @returns the content of the cell as a literal
     */
    Literal value(){
        return _content.value();
    }

    public boolean addObserver(Observer obs) {
        return _observers.add(obs);
    }
      
    public boolean removeObserver(Observer obs) {
        return _observers.remove(obs);
    }

    private void notifyObservers() {
        for (Observer obs : _observers)
          obs.update();
    }
}
