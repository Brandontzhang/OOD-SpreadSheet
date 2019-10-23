package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class Cell implements ICell {
  // content should be private, user should not be able to access the Sexp of the cell
  // if they want to see what is in the cell, they should call evaluate cell
  private Sexp content;    /// not saving Sexp as Cell -> saving the evaluated expression

  // constructors will probably need to become private..
  public Cell() {
    // representing a blank cell
    this.content = null;
  }
  public Cell(String content) {
    Parser cellParser = new Parser();
    this.content = cellParser.parse(content);
  }

  @Override
  public String evaluateCell() {
    // if the content isn't null, evaluate it, otherwise it is a blank cell so return ""
    return (this.content != null) ? this.content.toString() : "";
  }
}
