package xxl.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
// FIXME import classes
import xxl.core.exception.MissingFileAssociationException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    if(receiver.getSpreadsheet() != null && receiver.getSpreadsheet().noFilename()){
      addStringField("filename", Message.newSaveAs());
    }
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      if (!_receiver.getSpreadsheet().noFilename()) {
        _receiver.save();
      }
      else {
        _receiver.saveAs(stringField("filename"));
      }
    } catch (MissingFileAssociationException | IOException e) {
      throw new FileOpenFailedException(e);
      
    }
  }
}
