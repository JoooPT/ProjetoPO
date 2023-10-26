package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.Cell;

import java.util.List;

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

  DoShowValues(Spreadsheet receiver) {
    super(Label.SEARCH_VALUES, receiver);
    addStringField("input", Message.searchValue());
  }
  
  @Override
  protected final void execute() {
    List <Cell> cells = _receiver.searchValue(stringField("input"));
    for (Cell c: cells) {
      _display.addNewLine(c.value().toString(), false);
    }
  }
}
