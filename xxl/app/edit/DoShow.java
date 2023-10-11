package xxl.app.edit;

import java.util.Collection;
import java.util.List;
import xxl.core.Cell;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
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
    Range range = _receiver.createRange(stringField("gama"));
    List<Cell> list = range.getCells();
    for (Cell c: list) {
      _display.addNewLine(c, false);
    }
  }
}
