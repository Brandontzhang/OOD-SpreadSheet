package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;


/**
 * Visitor for sList.
 */

public class GetSListContent implements SexpVisitor {
  @Override
  public Object visitBoolean(boolean b) {
    return null;
  }

  @Override
  public Object visitNumber(double d) {
    return null;
  }

  @Override
  public Object visitSymbol(String s) {
    return null;
  }

  @Override
  public Object visitString(String s) {
    return null;
  }

  @Override
  public Object visitSList(List l) {
    ArrayList<Sexp> temp = new ArrayList<>();
    // Returns the list
    for (int i = 0; i < l.size(); i++) {
      temp.add((Sexp) l.get(i));
    }
    return temp;
  }
}
