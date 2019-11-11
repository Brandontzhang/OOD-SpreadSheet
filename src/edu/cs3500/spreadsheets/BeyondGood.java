package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;

import edu.cs3500.spreadsheets.model.IWorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorksheetBuild;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.ISpreadSheetView;
import edu.cs3500.spreadsheets.view.IView;
import edu.cs3500.spreadsheets.view.SpreadSheetGraphicsView;


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
    model.updateCell("B2", "1");
    IView view = new SpreadSheetGraphicsView( model);
    view.makeVisible();

    WorksheetBuild b1 = new WorksheetBuild();
    WorkSheet ws = new WorkSheet();

    Readable in = new StringReader("");
    for (String s: args) {
      try {
        in.read(CharBuffer.wrap(s));
      } catch (IOException e) {
        System.out.println("Error with IO");
      }

      // trying to use the reader they gave
    WorksheetReader.read(b1, in);

    String filePath;
    String content;
    String cellEval;

    // String formats
    // first line -in filename -eval cell
    // cell =(content)

//    for (String s: args) {
//      // reading in args
//      System.out.println(s);
//      if (args[0].equals("-in")) {
//        try {
//          FileReader fd = new FileReader(args[1]);
//          ws = WorksheetReader.read(b1, fd);
//        } catch (FileNotFoundException e) {
//          System.out.println("File not Found");
//        }
//        if (args[2].equals("-eval")) {
//          ws.getCell(args[3]);
//        }
//      } else {
//        System.out.println("Command-line was malformed");
//      }
    }


    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it,
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */
  }
}
