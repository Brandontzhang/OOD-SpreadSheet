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
    this.spreadSheet = new ArrayList<>();
  }

  @Override
  public ICell getCell(Coord c) {
    int row = c.row;
    int col = c.col;
    if (this.spreadSheet.get(row).get(col) == null) {
      Cell newEmptyCell = new Cell();
      this.spreadSheet.get(row).add(col, newEmptyCell);
      return newEmptyCell;
    } else {
      return this.spreadSheet.get(row).get(col);
    }
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

  @Override
  public ArrayList<ICell> getRegionCells(ICell c1, ICell c2) {
    // c1 should be before c2, if not throw an exception
    return null;
  }

  @Override
  public void updateCell(Coord c, Sexp s) {
    // have to first check if the container is big enough, if it isn't need to reallocate into a
    // larger array
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
