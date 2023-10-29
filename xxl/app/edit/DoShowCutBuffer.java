package xxl.app.edit;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.Cell;

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Cell> buffer =_receiver.getCutBuffer();
    for(Cell c: buffer){
      _display.addNewLine(_receiver.visualizeCellBuffer(c), false);
    }
  }
}
