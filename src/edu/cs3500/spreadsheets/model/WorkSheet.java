package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.sexp.Sexp;

public class WorkSheet implements IWorkSheet {
  // 2D arraylist of cells
  ArrayList<ArrayList<ICell>> spreadSheet = new ArrayList();

  // Might need to change constructor to private and use design patterns later on
  public WorkSheet(Readable rd) {
    // Insert data from readable into the spreadSheet
    // For now, just create a 2D array of cells
  }

  @Override
  public Coord getCoord(ICell c) {
    return null;
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
