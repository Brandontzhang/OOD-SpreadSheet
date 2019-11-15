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
import edu.cs3500.spreadsheets.view.IView;
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
  public static void main(String[] args) {

    IWorkSheet model = new WorkSheet();
    model.updateCell("A500", "27");
    WorksheetReader wsr = new WorksheetReader();
    WorksheetBuild b1 = new WorksheetBuild();
    WorkSheet ws = new WorkSheet();

    Readable in = new StringReader("");
    for (String s : args) {
      try {
        in.read(CharBuffer.wrap(s));
      } catch (IOException e) {
        System.out.println("Error with IO");
      }


      // trying to use the reader they gave


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
        //causing a nullpointerexception. I can only guess it's something to do with fd not
        //being used correctly

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
          System.out.println(tview.toString());
          fw.write(tview.toString());
          fw.close();
        } catch (IOException e) {
          System.out.println("IO exception when attempting to write to file");
        }

      } else if (args[2].equals("-gui")) {
        IView gui = new SpreadSheetGraphicsView(ws);
        gui.makeVisible();
      } else {
        System.out.println("Improperly formatted Command Line");
      }
    } else if (args[0].equals("-gui")) {
      IView gui = new SpreadSheetGraphicsView(new WorkSheet());
      gui.makeVisible();
    } else {
      System.out.println("Improperly formatted Command Line");
    }

    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it,
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */
  }
}
