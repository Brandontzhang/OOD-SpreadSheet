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
      Cell newCell = new Cell();
      this.spreadSheet.get(row).add(col, newCell);
      return newCell;
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
    return null;
  }

  @Override
  public void updateCell(Coord c, Sexp s) {
    // if there isn't a cell in this coordinate
    // create a cell
    if (spreadSheet.get(c.col).get(c.row) == null) {
      this.createCell(new Coord(c.col, c.row), s);
    }

  }

  private void createCell(Coord c, Sexp s) {

  }
}
