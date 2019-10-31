package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor to Sbooleans.
 */
public class ProcessSBoolean implements SexpVisitor {
  @Override
  public Object visitBoolean(boolean b) {
    return "" + b;
  }

  @Override
  public Object visitNumber(double d) {
    throw new IllegalArgumentException("Wrong type");
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
