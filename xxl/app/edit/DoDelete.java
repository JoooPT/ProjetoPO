package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidRangeException;

/**
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

  DoDelete(Spreadsheet receiver) {
    super(Label.DELETE, receiver);
    addStringField("range", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
    _receiver.clear(stringField("range"));
    }catch(InvalidRangeException e){
      throw new InvalidCellRangeException(e.getInvalidRange());
    }
  }
}

