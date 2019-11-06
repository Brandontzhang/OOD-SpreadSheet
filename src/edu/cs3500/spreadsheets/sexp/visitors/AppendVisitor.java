package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

public class AppendVisitor implements SexpVisitor<Sexp> {
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
    if (l.size() <= 0) {
      throw new IllegalArgumentException("Wrong number of arguments");
    }

    String buildstr = "";
    for (int i = 0; i < l.size(); i++) {
      buildstr = buildstr + l.get(i).toString();
    }

    String retstr = "\"";
    for (int i = 0; i < buildstr.length(); i++) {
      if(buildstr.charAt(i) != '\\' && buildstr.charAt(i) != '\"') {
        retstr = retstr + buildstr.charAt(i);
      }
    }
    retstr = retstr + "\"";

    Parser p = new Parser();
    Sexp ret = p.parse(retstr.toString());
    return ret;
  }
}
