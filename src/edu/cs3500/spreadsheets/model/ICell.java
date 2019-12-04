package edu.cs3500.spreadsheets.model;


import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Interface for a cell.
 */
public interface ICell {
  // update the cell with the input string
  void updateCell(String s);

  // return the contents of the cell as a Sexp
  Sexp getContent();

  // returns the contents of the cell unevaluated
  String getUnevalContent();
}
