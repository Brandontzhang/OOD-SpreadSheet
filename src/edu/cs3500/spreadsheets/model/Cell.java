package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

public class Cell implements ICell {
  Sexp content;

  // this will also probably need to become private..
  public Cell(Sexp content) {
    this.content = content;
  }

  @Override
  public Sexp evaluateCell() {
    return null;
  }
}
