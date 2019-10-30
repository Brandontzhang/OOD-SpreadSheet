package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.sexp.Sexp;

public interface IWorkSheet {
  // Returns the contents of the cell given the coordinates
  String getCell(String c);

  // Returns a list of values of the cells between the two inputs
  ArrayList<String> getRegionCells(String c1, String c2) throws IllegalArgumentException;

  // update a cell
  void updateCell(String c, String s);
}
