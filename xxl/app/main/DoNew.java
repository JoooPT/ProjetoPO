package xxl.app.main;

import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("rows",Message.lines());
    addIntegerField("columns",Message.columns());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if(Form.confirm(Message.saveBeforeExit())){
      if(_receiver.getSpreadsheet()!= null && _receiver.getSpreadsheet().changed()){
        try{
          if(_receiver.getSpreadsheet().noFilename()){
            _receiver.saveAs(Form.requestString(Message.saveAs()));
          } else {
            _receiver.save();
          }
        } catch (MissingFileAssociationException| IOException e) {
          throw new FileOpenFailedException(e);
        }  
      }
    }
    _receiver.createNewSpreadSheet(integerField("rows"), integerField("columns"));
  }

  public Boolean readBoolean(String prompt){
    String receiveMessage = "nothing";
    while(!receiveMessage.equals("n") && !receiveMessage.equals("s")){
      receiveMessage = Form.requestString(prompt);
      System.out.println(receiveMessage);
    }

    if(receiveMessage.equals("n")){
      return false;
    } else{
      return true;
    }
  }

}