package xxl.app.edit;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.Parser;
import xxl.core.Content;
// FIXME import classes
import xxl.core.exception.InvalidRangeException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    addStringField("range", Message.address());
    addStringField("content", Message.contents());
  }
  
  @Override
  protected final void execute() throws CommandException {
     try{
      Range range = _receiver.createRange(stringField("range"));
      List<Cell> list = range.getCells();
      Parser parse = new Parser(_receiver);
      Content content = parse.parseContent(stringField("content"));
      for (Cell c: list) {
        _receiver.insertContent(c.getRow(),c.getCol(),content);
      }
    } catch(InvalidRangeException e) {
      throw new InvalidCellRangeException(e.getInvalidRange());
    } catch(UnrecognizedEntryException e){
    }
  }
}
