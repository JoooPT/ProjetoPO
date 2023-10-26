package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
// FIXME import classes
import xxl.core.exception.InvalidRangeException;

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

  DoPaste(Spreadsheet receiver) {
    super(Label.PASTE, receiver);
    addStringField("range", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
    _receiver.paste(stringField("range"));
    }catch(InvalidRangeException e){
      throw new InvalidCellRangeException(e.getInvalidRange());
    }
  }
}
