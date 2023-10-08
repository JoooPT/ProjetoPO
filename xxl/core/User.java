package xxl.core;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class User {
    private String _name;
    private List<Spreadsheet> _spreadsheets;

    public User(String name){
        _name = name;
        _spreadsheets = new ArrayList<Spreadsheet>();
    }

    @Override
    public boolean equals(Object obj){ // Implement exception if obj isnt a user
        return ((User)obj)._name.equals(_name);
    }

    @Override
    public int hashCode(){
        return _name.hashCode();
    }

    void add(Spreadsheet sheet){
        _spreadsheets.add(sheet);
    }
}
