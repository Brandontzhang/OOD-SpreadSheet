package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class Cell implements ICell {
  // content should be private, user should not be able to access the Sexp of the cell
  // if they want to see what is in the cell, they should call evaluate cell
  private Sexp content;

  // this will also probably need to become private..
  public Cell(String content) {
    Parser cellParser = new Parser();
    this.content = cellParser.parse(content);
  }

  @Override
  public Sexp evaluateCell() {
    return null;
  }
}
