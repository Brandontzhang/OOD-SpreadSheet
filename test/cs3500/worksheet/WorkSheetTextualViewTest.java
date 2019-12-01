package cs3500.worksheet;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.controller.WorkSheetController;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorksheetBuild;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.SpreadSheetTextualView;

/**
 * Tests for WorkSheetTextualView.
 */

public class WorkSheetTextualViewTest {
  @Test
  public void testRoundTripTextualView() {
    WorksheetBuild b1 = new WorksheetBuild();
    WorkSheet ws = new WorkSheet();
    ws.updateCell("A1", "1");

    SpreadSheetTextualView tview = new SpreadSheetTextualView();
    WorkSheetController wsc = new WorkSheetController(ws, tview);
    try {
      FileWriter fw = new FileWriter("test.txt");
      fw.write(tview.toString());
      fw.close();
    } catch (IOException e) {
      System.out.println("IO exception when attempting to write to file");
    }

    try {
      FileReader fd = new FileReader("test.txt");
      ws = WorksheetReader.read(b1, fd);
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }

    assertEquals("1.0", ws.getCell("A1"));
  }
}
