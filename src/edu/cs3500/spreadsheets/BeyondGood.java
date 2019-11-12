package edu.cs3500.spreadsheets;

import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;

import edu.cs3500.spreadsheets.model.IWorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorksheetBuild;
import edu.cs3500.spreadsheets.model.WorksheetReader;
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
    model.updateCell("A1", "1");
    model.updateCell("B2", "2");
    model.updateCell("C3", "3");
    model.updateCell("D4", "4");
    model.updateCell("E5", "5");
    model.updateCell("F6", "6");
    model.updateCell("G7", "7");
    model.updateCell("H8", "8");
    model.updateCell("I9", "9");
    model.updateCell("J10", "10");
    model.updateCell("K11", "11");
    model.updateCell("L12", "12");
    model.updateCell("M13", "13");
    model.updateCell("N14", "14");
    model.updateCell("O15", "15");
    model.updateCell("P16", "16");
    model.updateCell("Q17", "17");
    model.updateCell("R18", "18");
    model.updateCell("S19", "19");
    model.updateCell("T20", "20");
    model.updateCell("U21", "21");
    model.updateCell("V22", "22");
    model.updateCell("W23", "23");
    model.updateCell("X24", "24");
    model.updateCell("Y25", "25");
    model.updateCell("Z26", "26");
    model.updateCell("AA27", "27");
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
