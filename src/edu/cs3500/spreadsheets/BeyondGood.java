package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.io.FileReader;
import java.io.FileWriter;

import edu.cs3500.spreadsheets.controller.WorkSheetController;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ISpreadSheet;
import edu.cs3500.spreadsheets.model.IWorkSheet;
import edu.cs3500.spreadsheets.model.IWorksheetProvider;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheetProvider;
import edu.cs3500.spreadsheets.model.WorksheetBuild;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.ISpreadSheetView;
import edu.cs3500.spreadsheets.view.SpreadSheetEditView;
import edu.cs3500.spreadsheets.view.SpreadSheetGraphicsView;
import edu.cs3500.spreadsheets.view.SpreadSheetTextualView;
import edu.cs3500.spreadsheets.view.provider.EditableGUIView;
import edu.cs3500.spreadsheets.view.provider.GUISheetView;
import edu.cs3500.spreadsheets.view.provider.NewEditableGUIView;
import edu.cs3500.spreadsheets.view.provider.SheetView;


/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) throws IOException {

    IWorkSheet model = new WorkSheet();
    model.updateCell("A1", "1234567");
    WorksheetReader wsr = new WorksheetReader();
    WorksheetBuild b1 = new WorksheetBuild();
    WorkSheet ws = new WorkSheet();

    // testing
    // ISpreadSheetView guiTest = new SpreadSheetGraphicsView(model);
    // guiTest.render();
    // ISpreadSheetView editTest = new SpreadSheetEditView();
    // WorkSheetController wsControl = new WorkSheetController(model, editTest);
    // wsControl.render();

    WorkSheetProvider ws2 = new WorkSheetProvider(model);
    EditableGUIView testView = new EditableGUIView(ws2);
    ISpreadSheetView providerView = new NewEditableGUIView(testView);
    WorkSheetController wsControl = new WorkSheetController(ws2, providerView);
    wsControl.render();

    Readable in = new StringReader("");
    for (String s : args) {
      try {
        in.read(CharBuffer.wrap(s));
      } catch (IOException e) {
        System.out.println("Error with IO");
      }
    }

    if (args[0].equals("-in")) {
      //get next s and read in file and skip next s\
      try {
        FileReader fd = new FileReader(args[1]);
        ws = WorksheetReader.read(b1, fd);
      } catch (FileNotFoundException e) {
        System.out.println("File not found.");
      }

      if (args[2].equals("-eval")) {
        //eval and this should be followed by a cell to print the contents of
        System.out.println(ws.getCell(args[3]));
      } else if (args[2].equals("-save")) {
        SpreadSheetTextualView tview = new SpreadSheetTextualView();
        WorkSheetController wsc = new WorkSheetController(ws, tview);
        try {
          FileWriter fw = new FileWriter(args[3]);
          fw.write(tview.toString());
          fw.close();
        } catch (IOException e) {
          System.out.println("IO exception when attempting to write to file");
        }

      } else if (args[2].equals("-gui")) {
        ISpreadSheetView gui = new SpreadSheetGraphicsView();
        WorkSheetController wsC1 = new WorkSheetController(ws, gui);
        gui.render();
      } else if (args[2].equals("-edit")) {
        ISpreadSheetView edit = new SpreadSheetEditView();
        WorkSheetController wsEdit = new WorkSheetController(ws, edit);
        wsEdit.render();
      } else {
        System.out.println("Improperly formatted Command Line");
      }
    } else if (args[0].equals("-gui")) {
      ISpreadSheetView gui = new SpreadSheetGraphicsView();
      WorkSheetController wsC2 = new WorkSheetController(ws, gui);
      wsC2.render();
    } else if (args[0].equals("-edit")) {
      ISpreadSheetView edit = new SpreadSheetEditView();
      WorkSheetController wsEdit = new WorkSheetController(ws, edit);
      wsEdit.render();
    } else {
      System.out.println("Improperly formatted Command Line");
    }
  }
}
