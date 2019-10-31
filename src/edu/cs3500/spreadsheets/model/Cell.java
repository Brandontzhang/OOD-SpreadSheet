package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Represents a cell in the worksheet.
 */
public class Cell implements ICell {
  // if they want to see what is in the cell, they should call evaluate cell
  private String content;

  // constructors will probably need to become private..
  public Cell() {
    // representing a blank cell
    this.content = "";
  }

  public Cell(boolean b) {
    this.updateCell("" + b);
  }

  public Cell(int i) {
    this.updateCell("" + i);
  }

  public Cell(String s) {
    this.updateCell(s);
  }

  @Override
  public void updateCell(String s) {
    this.content = s;
  }

  @Override
  public String viewCell() {
    // return a copy of the string so users cannot change it from the view
    String copy = content;
    return copy;
  }
}
