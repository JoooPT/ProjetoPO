package xxl.app.edit;

import java.util.Collection;
import java.util.List;

import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidRangeException;
import xxl.core.Range;

// FIXME import classes

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("gama", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      Range range = _receiver.createRange(stringField("gama"));
      List<Cell> list = range.getCells();
      for (Cell c: list) {
        _display.addNewLine(c, false);
      }
    } catch(InvalidRangeException e) {
      throw new InvalidCellRangeException(e.getInvalidRange());
    } 
  }
}
