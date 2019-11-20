package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.io.FileReader;
import java.io.FileWriter;

import edu.cs3500.spreadsheets.model.IWorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorksheetBuild;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.ISpreadSheetView;
import edu.cs3500.spreadsheets.view.SpreadSheetEditView;
import edu.cs3500.spreadsheets.view.SpreadSheetGraphicsView;
import edu.cs3500.spreadsheets.view.SpreadSheetTextualView;


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
    ISpreadSheetView editTest = new SpreadSheetEditView(model);
    editTest.render();

    Readable in = new StringReader("");
    for (String s : args) {
      try {
        in.read(CharBuffer.wrap(s));
      } catch (IOException e) {
        System.out.println("Error with IO");
      }

      String filePath;
      String content;
      String cellEval;

      // String formats
      // first line -in filename -eval cell
      // cell =(content)

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

        SpreadSheetTextualView tview = new SpreadSheetTextualView(ws);

        try {
          FileWriter fw = new FileWriter(args[3]);
          fw.write(tview.toString());
          fw.close();
        } catch (IOException e) {
          System.out.println("IO exception when attempting to write to file");
        }

      } else if (args[2].equals("-gui")) {
        ISpreadSheetView gui = new SpreadSheetGraphicsView(ws);
        gui.render();
      } else {
        System.out.println("Improperly formatted Command Line");
      }
    } else if (args[0].equals("-gui")) {
      ISpreadSheetView gui = new SpreadSheetGraphicsView(new WorkSheet());
      gui.render();
    } else {
      System.out.println("Improperly formatted Command Line");
    }
  }
}
