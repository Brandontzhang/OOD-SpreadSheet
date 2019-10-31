package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor for SNums.
 */
public class ProcessSNum implements SexpVisitor {
  @Override
  public Object visitBoolean(boolean b) {
    throw new IllegalArgumentException("Wrong type");
  }

  @Override
  public Object visitNumber(double d) {
    return "" + d;
  }

  @Override
  public Object visitSymbol(String s) {
    throw new IllegalArgumentException("Wrong type");
  }

  @Override
  public Object visitString(String s) {
    throw new IllegalArgumentException("Wrong type");
  }

  @Override
  public Object visitSList(List l) {
    throw new IllegalArgumentException("Wrong type");
  }
}
