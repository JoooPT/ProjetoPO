package xxl.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Calculator;
// FIXME import classes
import xxl.core.exception.MissingFileAssociationException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
  }
  
  @Override
  protected final void execute() {
    // FIXME implement command and create a local Form
    try {
      if (_receiver.getSpreadsheet().getFilename() != null) {
        _receiver.save();
      }
      else {
        addStringField("filename", Message.saveAs());
        _receiver.saveAs(stringField("filename"));
      }
    } catch (FileNotFoundException | MissingFileAssociationException e) {
      System.err.println("Error: "  + e.getMessage());
    } catch (IOException e) {
      System.err.println("Error: "  + e.getMessage());
    }
  }
}
