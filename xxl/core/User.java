package xxl.core;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class User implements Serializable{
    private final String _name;
    private List<Spreadsheet> _spreadsheets = new ArrayList<Spreadsheet>();

    /* Constructor */
    public User(String name){
        _name = name;
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

    /**
     * Adds a spreadsheet to the user.
     * 
     * @param sheet
     */
    void add(Spreadsheet sheet){
        _spreadsheets.add(sheet);
    }

    public final String getName() {
        return _name;
    }

    public List<Spreadsheet> getSpreadsheets() {
        return Collections.unmodifiableList(_spreadsheets);
    }
}
