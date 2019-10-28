package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

public class WorkSheet implements IWorkSheet {
  // 2D arraylist of cells
  private List<List<ICell>> spreadSheet;

  // Might need to change constructor to private and use design patterns later on
  public WorkSheet(Readable rd) {
    // Insert data from readable into the spreadSheet
    // For now, just create a 2D array of cells

    // for inputs out of bounds, make a loop from current size of spreadsheet up to input and insert
    // null in them until we get to the cell
    this.spreadSheet = new ArrayList<>(10000);
  }

  @Override
  // should return a string holding the content
  public String getCell(String in) {
    // Creating a coordinate to work with
    int row = this.getInputRow(in);
    int col = this.getInputColumn(in);
    Coord c = new Coord(row, col);

    // Check if the coordinate is out of bounds for current array list
    if (c.col > this.spreadSheet.size()) {
      // Column is out of bounds, create a larger array list
      List<List<ICell>> tempSheet = new ArrayList<>(this.spreadSheet.size() * 2);
      tempSheet.addAll(this.spreadSheet);
      this.spreadSheet = tempSheet;

      Cell newEmptyCell = new Cell();
      this.spreadSheet.get(row).add(col, newEmptyCell);
      return newEmptyCell;
    } else if (c.row > this.spreadSheet.get(c.col).size()) {
      // Row is out of bounds, create a larger array list at that spot
      List<ICell> tempSheet = new ArrayList<>(this.spreadSheet.get(c.col).size() * 2);
      tempSheet.addAll(this.spreadSheet.get(c.col));
      this.spreadSheet.set(c.col, tempSheet);

      Cell newEmptyCell = new Cell();
      this.spreadSheet.get(row).add(col, newEmptyCell);
      return newEmptyCell;
    }
    else if (this.spreadSheet.get(row).get(col) == null) {
      // Column and row are in bound, but have not been accessed before
      Cell newEmptyCell = new Cell();
      this.spreadSheet.get(row).add(col, newEmptyCell);
      return newEmptyCell;
    } else {
      // Column and row have been accessed and contain something
      return this.spreadSheet.get(row).get(col);
    }
  }

  // increasing the spreadsheet arraylist to size of input
  private ArrayList<> increaseSize(int n) {

  }

  // Given a string input referring to a cell, return the column (int form)
  private int getInputColumn(String s) {
    if (this.validCellAddress(s)) {
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

  // unecessary stuff, row is just a number
  // Given a string input referring to a cell, return the row (int form)
  private int getInputRow(String s) {
    if (this.validCellAddress(s)) {
      boolean foundInt = false;
      int retint = 0;
      for (int i = 0; i < s.length(); i++) {
        if (!Character.isLetter(s.charAt(i)) && !foundInt) {
          foundInt = true;
        } else {
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
  @Override
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

  @Override
  public ArrayList<ICell> getRegionCells(String c1, String c2) {
    // Check that c1 and c2 are both Strings inputs in the format <letters, number>
    if (!this.validCellAddress(c1) || !this.validCellAddress(c2)) {
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

  // method used to check that an input string is a valid cell address.
  // (Letters followed by numbers)
  // need to consider cases, A8, A100, A1000... BA1, ABA10, BABA100
  private boolean validCellAddress(String s) {
    boolean foundInt = false;
    // check for letters at the start of the string
    for (int position = 0; position < s.length(); position++) {
      if (!Character.isLetter(s.charAt(position)) && !foundInt) {
        // if the current char isn't a letter, start checking for numbers
        foundInt = true;
      } else if (!Character.isDigit(s.charAt(position))) {
        // if letters aren't followed by only ints, return false
        return false;
      }
    }
    return true;
  }

  @Override
  public void updateCell(String c, String s) {
    // have to first check if the container is big enough, if it isn't need to reallocate into a
    // larger array since array lists only resize when appending onto the list is larger, not when
    // adding onto a random index much larger
    if (c.col > this.spreadSheet.size()) {
      List<List<ICell>> tempSheet = new ArrayList<>(this.spreadSheet.size() * 2);
      tempSheet.addAll(this.spreadSheet);
      this.spreadSheet = tempSheet;
    }

    if (c.row > this.spreadSheet.get(c.col).size()) {
      List<ICell> tempSheet = new ArrayList<>(this.spreadSheet.get(c.col).size() * 2);
      tempSheet.addAll(this.spreadSheet.get(c.col));
      this.spreadSheet.set(c.col, tempSheet);
    }

    if (spreadSheet.get(c.col).get(c.row) == null) {
      // if there isn't a cell in this coordinate
      // create a cell
      this.createCell(new Coord(c.col, c.row), s);
    } else {
      // update the cell at this spot, need to decide if creating new one or changing the current
      ICell cell;
      cell = this.spreadSheet.get(c.col).get(c.row);
      cell.updateCell(s);
    }
  }

  // Create a cell and add it to the appropriate spot
  private void createCell(String c, String s) {
    ICell newCell = new Cell(s);
    this.spreadSheet.get(c.col).add(c.row, newCell);
  }
}
