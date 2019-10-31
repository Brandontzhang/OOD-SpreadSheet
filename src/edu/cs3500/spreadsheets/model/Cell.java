package edu.cs3500.spreadsheets.model;

import java.util.Stack;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSBoolean;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSList;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSNum;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSString;
import edu.cs3500.spreadsheets.sexp.visitors.ProcessSSymbol;

public class Cell implements ICell {
  // if they want to see what is in the cell, they should call evaluate cell
  private String content;

  // have another list to track what other cells are holding?

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
    //Parser cellParser = new Parser();
    //this.content = this.evaluateInput(cellParser.parse(s));
    this.content = s;
  }

  @Override
  public String viewCell() {
    // return a copy of the string so users cannot change it from the view
    String copy = content;
    return copy;
  }

  // evaluate a cell
  private String evaluateInput(Sexp s){
    Stack<String> evaluateStack = new Stack<>();
    if (s instanceof SNumber) {
      return (String) s.accept(new ProcessSNum());
    } else if (s instanceof SString) {
      return (String) s.accept(new ProcessSString());
    } else if (s instanceof SSymbol) {
      return (String) s.accept(new ProcessSSymbol());
    } else if (s instanceof SBoolean) {
      return (String) s.accept(new ProcessSBoolean());
    } else if (s instanceof SList) {
      return "" + s.accept(new ProcessSList());
    } else {
      return "";
    }
  }
}
