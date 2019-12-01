package cs3500.worksheet;

import org.junit.Test;

import edu.cs3500.spreadsheets.controller.WorkSheetController;
import edu.cs3500.spreadsheets.model.IWorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.view.ISpreadSheetView;
import edu.cs3500.spreadsheets.view.SpreadSheetGraphicsView;
import edu.cs3500.spreadsheets.view.SpreadSheetTextualView;

import static org.junit.Assert.assertEquals;

/**
 * Tests that the controller works with different views.
 */
public class WorkSheetControllerTest {

  @Test
  public void controllerTest() {
    IWorkSheet ws = new WorkSheet();
    ws.updateCell("A9", "5");
    ISpreadSheetView tview = new SpreadSheetTextualView();
    WorkSheetController control = new WorkSheetController(ws, tview);
    assertEquals("A9 =5.0\n", tview.toString());
  }

  @Test
  public void controllerTest2() {
    IWorkSheet ws = new WorkSheet();
    ws.updateCell("A11", "4");
    ws.updateCell("B11", "5");
    ISpreadSheetView tview = new SpreadSheetTextualView();
    WorkSheetController control = new WorkSheetController(ws, tview);
    assertEquals("A11 =4.0\nB11 =5.0\n", tview.toString());
  }

  @Test
  public void controllerTest3() {
    WorkSheet ws = new WorkSheet();
    ws.updateCell("A11", "4");
    ws.updateCell("B11", "5");
    ISpreadSheetView gview = new SpreadSheetGraphicsView();
    WorkSheetController control = new WorkSheetController(ws, gview);
  }
}
