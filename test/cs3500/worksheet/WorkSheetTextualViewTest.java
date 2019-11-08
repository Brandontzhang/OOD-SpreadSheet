package cs3500.worksheet;

import org.junit.Test;

import java.io.IOException;

import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.view.SpreadSheetTextualView;

public class WorkSheetTextualViewTest {
  @Test
  public void testTextualView() throws IOException {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUB 3 1)");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    System.out.println(view.toString());
  }
}
