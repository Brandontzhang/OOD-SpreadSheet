package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

public interface ICell {
  // evaluate the cell itself
  Sexp evaluateCell();
}
