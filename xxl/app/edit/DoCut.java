package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidRangeException;

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

  DoCut(Spreadsheet receiver) {
    super(Label.CUT, receiver);
    addStringField("range", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      String range = stringField("range");
    _receiver.copy(range);
    _receiver.clear(range);
    }catch(InvalidRangeException e){
      throw new InvalidCellRangeException(e.getInvalidRange());
    }
  }
}

