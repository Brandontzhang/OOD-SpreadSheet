package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class Cell extends ACell {
  // content should be private, user should not be able to access the Sexp of the cell
  // if they want to see what is in the cell, they should call evaluate cell
  private String content;

  // constructors will probably need to become private..
  public Cell() {
    // representing a blank cell
    this.content = "";
  }
  public Cell(String content) {
    this.updateCell(content);
  }

  @Override
  public void updateCell(String s) {
    if (content.equals("")) {
      this.content = "";
    } else {
      Parser cellParser = new Parser();
      this.content = cellParser.parse(content).toString();
    }
  }
}
