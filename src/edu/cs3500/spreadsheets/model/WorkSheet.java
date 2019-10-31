package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSBoolean;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSList;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSNum;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSString;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSSymbol;

public class WorkSheet implements IWorkSheet {
  // 2D arraylist of cells
  private List<List<ICell>> spreadSheet;
  private int spreadSheetSize = 10;

  // Might need to change constructor to private and use design patterns later on
  public WorkSheet() {

    // for inputs out of bounds, make a loop from current size of spreadsheet up to input and insert
    // null in them until we get to the cell
    this.spreadSheet = new ArrayList<>(spreadSheetSize);

    // setting initial spreadsheet to empty cells (100 x 100 cells)
    for (int i = 0; i < spreadSheetSize; i++) {
      this.spreadSheet.add(new ArrayList<>(spreadSheetSize));
      for (int j = 0; j < spreadSheetSize; j++)
        this.spreadSheet.get(i).add(j, new Cell());
    }
  }

  @Override
  // should return a string holding the content
  public String getCell(String in) {
    // Creating a coordinate to work with
    int row = this.getInputRow(in);
    int col = this.getInputColumn(in);
    Coord c = new Coord(row, col);
    // Check if the coordinate is out of bounds for current array list
    if ((c.col >= this.spreadSheetSize) || (c.row >= this.spreadSheetSize)) {
      // Column is out of bounds, create a larger array list
      this.increaseSize(row, col);
    }
    // return content of cell
    return this.spreadSheet.get(col).get(row).viewCell();

  }

  // increasing the spreadsheet arraylist to size of input
  private List<List<ICell>> increaseSize(int row, int col) {


    int colSize = this.spreadSheet.size();
    for(int i = 0; i <= col - colSize; i++){
      this.spreadSheet.add(new ArrayList<ICell>(this.spreadSheet.get(0).size()));
    }
    for(int i = 0; i < this.spreadSheet.size(); i++){
      int rowSize = this.spreadSheet.get(i).size();
      for(int j = 0; j <= row - rowSize; j++){
        this.spreadSheet.get(i).add(new Cell());
      }
    }


    return this.spreadSheet;
  }

  // Given a string input referring to a cell, return the column (int form)
  private int getInputColumn(String s) {
    if (validCellAddress(s)) {
      String retstr = "";
      for (int i = 0; i < s.length(); i++) {
        if (!Character.isLetter(s.charAt(i))) {
          // if the current char isn't a letter, start checking for numbers
          return Coord.colNameToIndex(retstr);
        } else {
          // can replace with string builder
          retstr = retstr + s.charAt(i);
        }
      }
      return Coord.colNameToIndex(retstr);
    } else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  // Given a string input referring to a cell, return the row (int form)
  private int getInputRow(String s) {
    if (validCellAddress(s)) {
      int retint = 0;
      for (int i = 0; i < s.length(); i++) {
        if (!Character.isLetter(s.charAt(i))) {
          retint = Integer.parseInt(s.substring(i));
          return retint;
        }
      }
      return retint;
    } else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  // might be useful as a private method
  // might need to hide this away, should users ever have access to the cell?
  private Coord getCoord(ICell c) {
    for (int i = 0; i < this.spreadSheet.size(); i++) {
      for (int j = 0; j < this.spreadSheet.get(i).size(); j++) {
        if (c.equals(this.spreadSheet.get(i).get(j))) {
          return new Coord(i, j);
        }
      }
    }
    throw new IllegalArgumentException("Input cell does not exist");
  }

  @Override  // private? might not even need this function
  public ArrayList<String> getRegionCells(String c1, String c2) {
    // Check that c1 and c2 are both Strings inputs in the format <letters, number>
    if (!validCellAddress(c1) || !validCellAddress(c2)) {
      throw new IllegalArgumentException("Invalid input strings for cells");
    }
    // Creating two coordinate to work with
    int row1 = this.getInputRow(c1);
    int col1 = this.getInputColumn(c1);
    Coord coord1 = new Coord(row1, col1);
    int row2 = this.getInputRow(c2);
    int col2 = this.getInputColumn(c2);
    Coord coord2 = new Coord(row2, col2);

    // c1 should be before c2, if not throw an exception
    if (row1 > row2 || col1 > col2) {
      throw new IllegalArgumentException("Invalid ordering of inputs. Order should be left to right, top to bottom");
    }
    return null;
  }

  @Override
  public void updateCell(String c, String s) {
    int row = this.getInputRow(c);
    int col = this.getInputColumn(c);
    Coord coordinate = new Coord(row, col);

    if (coordinate.col > this.spreadSheet.size()
            || coordinate.row > this.spreadSheet.get(coordinate.col).size()) {
      this.increaseSize(coordinate.col, coordinate.row);
    }

    Parser cellParser = new Parser();
    String update = this.evaluateInput(cellParser.parse(s));
    this.spreadSheet.get(coordinate.col).get(coordinate.row).updateCell(update);
  }

  // method used to check that an input string is a valid cell address.
  // (Letters followed by numbers)
  // need to consider cases, A8, A100, A1000... BA1, ABA10, BABA100
  public static boolean validCellAddress(String s) {
    boolean foundInt = false;
    // check for letters at the start of the string
    for (int position = 0; position < s.length(); position++) {
      if (!Character.isLetter(s.charAt(position)) && !foundInt) {
        // if the current char isn't a letter, start checking for numbers
        foundInt = true;
      } else if (!Character.isDigit(s.charAt(position)) && foundInt) {
        // if letters aren't followed by only ints, return false
        return false;
      }
    }
    return true;
  }

  // evaluate a cell
  private String evaluateInput(Sexp s){
    if (s instanceof SNumber) {
      return (String) s.accept(new ProcessSNum());
    } else if (s instanceof SString) {
      return (String) s.accept(new ProcessSString());
    } else if (s instanceof SSymbol) {
      return (String) s.accept(new ProcessSSymbol());
    } else if (s instanceof SBoolean) {
      return (String) s.accept(new ProcessSBoolean());
    } else if (s instanceof SList) {
      return "" + s.accept(new ProcessSList());
    } else {
      return "";
    }
  }
}
