package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor to SSymbol.
 */
public class ProcessSSymbol implements SexpVisitor {
  @Override
  public Object visitBoolean(boolean b) {
    throw new IllegalArgumentException("Wrong type");
  }

  @Override
  public Object visitNumber(double d) {
    throw new IllegalArgumentException("Wrong type");
  }

  @Override
  public Object visitSymbol(String s) {
    return s;
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
