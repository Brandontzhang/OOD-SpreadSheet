package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.model.Coord;

public class WorkSheet implements IWorkSheet {
  // 2D arraylist of cells
  List<List<ICell>> spreadSheet;

  // Might need to change constructor to private and use design patterns later on
  public WorkSheet(Readable rd) {
    // Insert data from readable into the spreadSheet
    // For now, just create a 2D array of cells
    this.spreadSheet = new ArrayList<>(10000);
  }

  @Override
  public ICell getCell(String in) {
    // first check if string c is a valid input
    if (!this.validCellAddress(in)) {
      throw new IllegalArgumentException("Invalid input for cell");
    }
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

  // Given a string input referring to a cell, return the column (int form)
  private int getInputColumn(String s) {
    return 0;
  }

  // Given a string input referring to a cell, return the row (int form)
  private int getInputRow(String s) {
    return 0;
  }

  // might need to hide this away, should users ever have access to the cell?
  @Override
  public Coord getCoord(ICell c) {
    for (int i = 0; i < this.spreadSheet.size(); i++) {
      for (int j = 0; j < this.spreadSheet.get(i).size(); j++) {
        if (c.equals(this.spreadSheet.get(i).get(j))) {
          return new Coord(i, j);
        }
      }
    }
    throw new IllegalArgumentException("Input cell does not exist");
  }


  // If we want to hide functionality, should user even see coords?
  // take in strings instead?
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

  // method used to check that an input string is a valid cell address
  // need to consider cases, A8, A100, A1000... BA1, ABA10, BABA100
  private boolean validCellAddress(String s) {
    return false;
  }

  @Override
  public void updateCell(Coord c, Sexp s) {
    // have to first check if the container is big enough, if it isn't need to reallocate into a
    // larger array since arraylists only resize when appending onto the list is larger, not when
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
    }

  }

  private void createCell(Coord c, Sexp s) {

  }
}
