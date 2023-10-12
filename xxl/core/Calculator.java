package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.InvalidRangeException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {
  
  private Spreadsheet _spreadsheet; /* The current spreadsheet. */
  private Map<String,User> _users;
  private User _userActive;

  /* Constructor */
  public Calculator(){
    _users = new HashMap<String,User>();
    _userActive = new User("root");
    _users.put("root",_userActive);
  }

  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be null.
   */
  public final Spreadsheet getSpreadsheet() {
    return _spreadsheet;
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    try (ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(_spreadsheet.getFilename()))) {
      _spreadsheet.setChangedStatus(false);
      obOut.writeObject(_spreadsheet);
    }
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    // FIXME implement serialization method
    try (ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(filename))) {
      _spreadsheet.setFileName(filename);
      _spreadsheet.setChangedStatus(false);
      obOut.writeObject(_spreadsheet);
    }
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException, IOException, ClassNotFoundException {
    try (ObjectInputStream objIn =  new ObjectInputStream(new FileInputStream(filename))) {
      Object anObject = objIn.readObject();
      _spreadsheet = (Spreadsheet)anObject;
    }
  }
  
  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   * @throws IOException
   * @throws InvalidRangeException
   */
  public void importFile(String filename) throws ImportFileException, IOException, InvalidRangeException {
    try {
      _spreadsheet = new Parser().parseFile(filename);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  }
  
  /**
   * Creates a new spreadsheet.
   * @param rows row size of the New spreadsheet.
   * @param columns column size of the New spreadsheet.
   */
  public void createNewSpreadSheet(int rows, int columns){
    _spreadsheet = new Spreadsheet(rows, columns);
    _spreadsheet.addUser(_userActive);
    _userActive.add(_spreadsheet);
  }

  /**
   * Creates a new User and sets him as active
   * @param name of the new user  
   * @return true if a new user was sucessfuly created, false otherwise
   */
  public boolean createUser(String name){
    if(!_users.containsKey(name)){
      User newUser = new User(name);
      _userActive = newUser;
      _users.put(name,newUser);
    return true;
    }
    else {return false;}
  }

  /**
   * Changes the active user of the application.
   * @param name of the new active user
   */
  public void setActiveUser(String name){
    if(_users.containsKey(name)){
    _userActive = _users.get(name);
    }
  }
  
}
