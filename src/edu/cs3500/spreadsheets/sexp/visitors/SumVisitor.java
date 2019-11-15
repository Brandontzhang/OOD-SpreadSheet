package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor for Sum Functionality.
 */

public class SumVisitor implements SexpVisitor<Sexp> {
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

    double sum = 0;
    for (int i = 0; i < l.size(); i++) {
      if (!(l.get(i) instanceof SNumber)) {
        throw new IllegalArgumentException("Wrong type of arguments");
      }
      sum = sum + Double.parseDouble(l.get(i).toString());
    }

    String retstr = "" + sum;

    Parser p = new Parser();
    Sexp ret = p.parse(retstr);
    return ret;
  }
}
