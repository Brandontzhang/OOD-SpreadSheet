package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor for Product Functionality.
 */

public class ProductVisitor implements SexpVisitor<Sexp> {
  @Override
  public Sexp visitBoolean(boolean b) {
    return null;
  }

  @Override
  public Sexp visitNumber(double d) {
    return null;
  }

  @Override
  public Sexp visitSymbol(String s) {
    return null;
  }

  @Override
  public Sexp visitString(String s) {
    return null;
  }

  @Override
  public Sexp visitSList(List l) {
    if (l.size() < 2) {
      throw new IllegalArgumentException("Wrong number of arguments");
    }

    double prod = 1;
    for (int i = 0; i < l.size(); i++) {
      if (!(l.get(i) instanceof SNumber)) {
        throw new IllegalArgumentException("Wrong type of arguments");
      }
      prod = prod * Double.parseDouble(l.get(i).toString());
    }

    String retstr = "" + prod;

    Parser p = new Parser();
    Sexp ret = p.parse(retstr);
    return ret;
  }
}
