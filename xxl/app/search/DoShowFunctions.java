package xxl.app.search;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cell;
import xxl.core.Spreadsheet;

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("input", Message.searchFunction());
  }

  @Override
  protected final void execute() {
    List <Cell> cells = _receiver.searchFunction(stringField("input"));
    for (Cell c: cells) {
      _display.addNewLine(c.toString(), false);
    }
  }
}
