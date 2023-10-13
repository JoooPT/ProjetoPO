package xxl.core;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    private String _name;
    private List<Spreadsheet> _spreadsheets;

    /* Constructor */
    public User(String name){
        _name = name;
        _spreadsheets = new ArrayList<Spreadsheet>();
    }

    @Override
    public boolean equals(Object obj) {
        String name = null;
        try {
            name = ((User)obj)._name;
        } catch (ClassCastException e) {
            System.err.println(e.getMessage());
        }
        return name.equals(_name);
    }

    @Override
    public int hashCode(){
        return _name.hashCode();
    }

    /**
     * Adds a spreadsheet to the user.
     * 
     * @param sheet
     */
    void add(Spreadsheet sheet){
        _spreadsheets.add(sheet);
    }
}
