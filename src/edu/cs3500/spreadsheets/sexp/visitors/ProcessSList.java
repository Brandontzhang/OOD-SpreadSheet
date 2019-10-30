package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;
import java.util.Stack;

import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

public class ProcessSList implements SexpVisitor {
  @Override
  public Object visitBoolean(boolean b) {
    return b;
  }

  @Override
  public Object visitNumber(double d) {
    return d;
  }

  @Override
  public Object visitSymbol(String s) {
    return s;
  }

  @Override
  public Object visitString(String s) {
    return s;
  }

  @Override
  public Object visitSList(List l) {
    Stack<Object> trackStack = new Stack();
    int endList = l.size() - 1;
    for (int i = endList; i >= 0; i--) {
      switch(l.get(i).toString()) {
        case "PRODUCT":
          break;
        case "SUM":
          break;
        case "SUB":
          // too many items in expression
          if (trackStack.size() > 2) {
            throw new IllegalArgumentException("Incorrect number of inputs for expression");
          }

          // first item in expression
          if ((!(trackStack.peek() instanceof SNumber)) && (!(trackStack.peek() instanceof SList))) {
            throw new IllegalArgumentException("Incorrect inputs for expression");
          }
          // removing first item from stack
          double first = (double) ((Sexp) trackStack.pop()).accept(new ProcessSList());

          // checking second item in stack is valid for SUB operation
          if ((!(trackStack.peek() instanceof SNumber)) && (!(trackStack.peek() instanceof SList))) {
            throw new IllegalArgumentException("Incorrect inputs for expression");
          }
          // removing second item from stack
          double second = (double) ((Sexp) trackStack.pop()).accept(new ProcessSList());

          // pushing answer onto the stack
          trackStack.push(first - second);
          break;
        case "<":
          if (trackStack.size() > 2) {
            throw new IllegalArgumentException("Incorrect number of inputs for expression");
          }

          // checking first item in expression is valid
          if ((!(trackStack.peek() instanceof SNumber)) && (!(trackStack.peek() instanceof SList))) {
            throw new IllegalArgumentException("Incorrect inputs for expression");
          }
          // removing first item from stack
          double intOne = (double) ((Sexp) trackStack.pop()).accept(new ProcessSList());

          // checking second item in expression is valid
          if ((!(trackStack.peek() instanceof SNumber)) && (!(trackStack.peek() instanceof SList))) {
            throw new IllegalArgumentException("Incorrect inputs for expression");
          }
          // removing first item from stack
          double intTwo = (double) ((Sexp) trackStack.pop()).accept(new ProcessSList());

          // pushing answer onto the stack
          trackStack.push(intOne < intTwo);
          break;
        case "APPEND":
          break;
        default:
          trackStack.push(l.get(i));
          break;
      }
    }
    return trackStack.pop();
  }
}
