package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

public class LessThanVisitor implements SexpVisitor<Sexp> {
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
    if (l.size() != 2) {
      throw new IllegalArgumentException("Wrong number of arguments");
    }

    if (!(l.get(0) instanceof SNumber) || !(l.get(1) instanceof SNumber)) {
      throw new IllegalArgumentException("Wrong type of arguments");
    }

    double one = Double.parseDouble(l.get(0).toString());
    double two = Double.parseDouble(l.get(1).toString());

    boolean ans = one < two;
    String retstr = "" + ans;

    Parser p = new Parser();
    Sexp ret = p.parse(retstr);
    return ret;
  }
}
