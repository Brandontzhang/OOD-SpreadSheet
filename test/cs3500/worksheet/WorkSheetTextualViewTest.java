package cs3500.worksheet;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.view.SpreadSheetTextualView;

/**
 * Tests for WorkSheetTextualView
 */

public class WorkSheetTextualViewTest {
  @Test
  public void testTextualViewSub() throws IOException {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUB 3 1)");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A1 =2.0\n", view.toString());
  }
  
  @Test
  public void testTextualViewNum() throws IOException {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "1");
    test.updateCell("A2", "2");
    test.updateCell("A3", "3");
    test.updateCell("A4", "4");
    test.updateCell("A5", "(PRODUCT A1:A4)");

    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A1 =1.0\n" +
            "A2 =2.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =24.0\n", view.toString());
    test.updateCell("A2", "4");
    assertEquals("A1 =1.0\n" +
            "A2 =2.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =24.0\n" +
            "A1 =1.0\n" +
            "A2 =4.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =48.0\n", view.toString());
  }
  @Test
  public void testTextualViewString() throws IOException {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "hello");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A1 =hello\n", view.toString());
  }
}
