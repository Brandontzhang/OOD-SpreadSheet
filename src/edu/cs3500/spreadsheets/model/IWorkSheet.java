package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.sexp.Sexp;

public interface IWorkSheet {
  // Returns the cell given the coordinates
  ICell getCell(String c);

  // Returns the coordinate of the input cell
  Coord getCoord(ICell c);

  // Returns a list of arrays of cells
  ArrayList<ICell> getRegionCells(String c1, String c2) throws IllegalArgumentException;

  // update a cell
  void updateCell(Coord c, Sexp s);
}
