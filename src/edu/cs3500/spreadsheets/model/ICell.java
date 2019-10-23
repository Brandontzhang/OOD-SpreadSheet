package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

public interface ICell {
  // update the cell
  void updateCell(String s);

  // evaluate the cell itself
  String evaluateCell();
}
