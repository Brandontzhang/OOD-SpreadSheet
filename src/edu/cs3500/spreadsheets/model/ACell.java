package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Sexp;

public abstract class ACell {
  // update the cell
  public abstract void updateCell(String s);

  // evaluate a cell
  public String evaluateCell(){
    return "";
  }
}
