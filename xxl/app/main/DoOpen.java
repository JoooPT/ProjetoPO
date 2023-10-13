package xxl.app.main;

import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.exception.UnavailableFileException;
import xxl.app.exception.FileOpenFailedException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("filename", Message.openFile());
  }
  
  @Override
  protected final void execute() throws CommandException {
    
    if(_receiver.getSpreadsheet()!= null && _receiver.getSpreadsheet().changed()){
      if(Form.confirm(Message.saveBeforeExit())){  
        Command<Calculator> save = new DoSave(_receiver);
        save.performCommand();
      }
    }
      try {
        String filename = stringField("filename");
        _receiver.load(filename);
      } catch (UnavailableFileException | IOException | ClassNotFoundException e) {
        throw new FileOpenFailedException(e);
      }
  }
}
