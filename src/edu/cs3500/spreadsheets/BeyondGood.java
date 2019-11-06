package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.WorksheetBuild;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    WorksheetBuild b1 = new WorksheetBuild();
    b1.createWorksheet();

    // String formats
    // first line -in filename -eval cell
    // cell =(content)

    for (String s: args) {
      // reading in args
      System.out.println(s);
      b1.createCell(1,2,"3");
    }
    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it,
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */
  }
}
