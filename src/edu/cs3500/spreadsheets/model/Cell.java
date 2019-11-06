package edu.cs3500.spreadsheets.model;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents a cell in the worksheet.
 */
public class Cell implements ICell {
  // if they want to see what is in the cell, they should call evaluate cell
  private String content;

  public Cell() {
    this.content = null;
  }

  // constructors will probably need to become private..
  public Cell(String content) {
    // representing a blank cell
    this.content = content;
  }

  @Override
  public void updateCell(String s) {
    this.content = s;
  }

  @Override
  public Sexp getContent() {
    // returns the cell's content as an Sexp
    if (this.content == null) {
      return null;
    }
    Parser parser = new Parser();
    return parser.parse(this.content);
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
