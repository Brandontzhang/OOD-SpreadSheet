package edu.cs3500.spreadsheets.sexp.visitors;

import java.util.List;
import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ICell;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor to SList.
 */
public class ProcessSList implements SexpVisitor {
  private List<List<ICell>> spreadSheet;

  public ProcessSList(List<List<ICell>> spreadSheet) {
    this.spreadSheet = spreadSheet;
  }

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
      switch (l.get(i).toString()) {
        case "PRODUCT":
          // too few items in expression
          if (trackStack.size() <= 1 && !(trackStack.peek() instanceof SSymbol)) {
            throw new IllegalArgumentException("Incorrect number of inputs for expression");
          }

          double prod = 1;
          // have to process everything within the stack
          while (trackStack.size() > 0) {
            if (trackStack.peek() instanceof SSymbol) {
              // Check if it is a reference to a group of cells (C1:C2)
              if (trackStack.peek().toString().matches("(.*):(.*)")) {
                String input = trackStack.peek().toString();
                String coord1 = input.substring(0, 2);
                String coord2 = input.substring(3, 5);
                int col1 = this.getInputColumn(coord1);
                int row1 = this.getInputRow(coord1);
                int col2 = this.getInputColumn(coord2);
                int row2 = this.getInputRow(coord2);
                if (col1 == col2) {
                  //iterate through the rows
                  for (int t = row1; t <= row2; t++) {
                    double item = Double.parseDouble(this.spreadSheet.get(col1).get(t).viewCell());
                    prod *= item;
                  }
                  trackStack.pop();
                } else if (row1 == row2) {
                  //iterate through the columns
                  for (int t = col1; t <= col2; t++) {
                    double item = Double.parseDouble(this.spreadSheet.get(t).get(row1).viewCell());
                    prod *= item;
                  }
                  trackStack.pop();
                } else {
                  throw new IllegalArgumentException("Incorrect cell formatting");
                }
              } else if (WorkSheet.validCellAddress(trackStack.peek().toString())) {
                // if it is a reference to a cell, get the contents of the cell
                int col = this.getInputColumn(trackStack.peek().toString());
                int row = this.getInputRow(trackStack.peek().toString());
                double item = Double.parseDouble(this.spreadSheet.get(col).get(row).viewCell());
                prod *= item;
                trackStack.pop();
              } else {
                throw new IllegalArgumentException("Invalid input");
              }
            } else if ((!(trackStack.peek() instanceof SNumber))
                    && (!(trackStack.peek() instanceof SList))) {
              throw new IllegalArgumentException("Incorrect inputs for expression");
            } else {
              // removing item from stack
              double item = (double)
                      ((Sexp) trackStack.pop()).accept(new ProcessSList(this.spreadSheet));
              prod *= item;
            }
          }
          trackStack.push(prod);
          break;
        case "SUM":
          // too few items in expression
          if (trackStack.size() <= 1 && !(trackStack.peek() instanceof SSymbol)) {
            throw new IllegalArgumentException("Incorrect number of inputs for expression");
          }

          double sum = 0;
          // have to process everything within the stack
          while (trackStack.size() > 0) {
            if (trackStack.peek() instanceof SSymbol) {
              // Check if it is a reference to a group of cells (C1:C2)
              if (trackStack.peek().toString().matches("(.*):(.*)")) {
                String input = trackStack.peek().toString();
                String coord1 = input.substring(0, 2);
                String coord2 = input.substring(3, 5);
                int col1 = this.getInputColumn(coord1);
                int row1 = this.getInputRow(coord1);
                int col2 = this.getInputColumn(coord2);
                int row2 = this.getInputRow(coord2);
                if (col1 == col2) {
                  //iterate through the rows
                  for (int t = row1; t <= row2; t++) {
                    double item = Double.parseDouble(this.spreadSheet.get(col1).get(t).viewCell());
                    sum += item;
                  }
                  trackStack.pop();
                } else if (row1 == row2) {
                  //iterate through the columns
                  for (int t = col1; t <= col2; t++) {
                    double item = Double.parseDouble(this.spreadSheet.get(t).get(row1).viewCell());
                    sum += item;
                  }
                  trackStack.pop();
                } else {
                  throw new IllegalArgumentException("Incorrect cell formatting");
                }
              } else if (WorkSheet.validCellAddress(trackStack.peek().toString())) {
                // if it is a reference to a cell, get the contents of the cell
                int col = this.getInputColumn(trackStack.peek().toString());
                int row = this.getInputRow(trackStack.peek().toString());
                double item = Double.parseDouble(this.spreadSheet.get(col).get(row).viewCell());
                sum += item;
                trackStack.pop();
              } else {
                throw new IllegalArgumentException("Invalid input");
              }
            } else if ((!(trackStack.peek() instanceof SNumber))
                    && (!(trackStack.peek() instanceof SList))) {
              throw new IllegalArgumentException("Incorrect inputs for expression");
            } else {
              // removing item from stack
              double item = (double)
                      ((Sexp) trackStack.pop()).accept(new ProcessSList(this.spreadSheet));
              sum += item;
            }
          }
          trackStack.push(sum);
          break;
        case "SUB":
          // too many or too few items in expression
          if (trackStack.size() != 2) {
            throw new IllegalArgumentException("Incorrect number of inputs for expression");
          }

          // first item in expression
          if ((!(trackStack.peek() instanceof SNumber))
                  && (!(trackStack.peek() instanceof SList))) {
            throw new IllegalArgumentException("Incorrect inputs for expression");
          }
          // removing first item from stack
          double first = (double)
                  ((Sexp) trackStack.pop()).accept(new ProcessSList(this.spreadSheet));

          // checking second item in stack is valid for SUB operation
          if ((!(trackStack.peek() instanceof SNumber))
                  && (!(trackStack.peek() instanceof SList))) {
            throw new IllegalArgumentException("Incorrect inputs for expression");
          }
          // removing second item from stack
          double second = (double)
                  ((Sexp) trackStack.pop()).accept(new ProcessSList(this.spreadSheet));

          // pushing answer onto the stack
          trackStack.push(first - second);
          break;
        case "<":
          if (trackStack.size() != 2) {
            throw new IllegalArgumentException("Incorrect number of inputs for expression");
          }

          // checking first item in expression is valid
          if ((!(trackStack.peek() instanceof SNumber))
                  && (!(trackStack.peek() instanceof SList))) {
            throw new IllegalArgumentException("Incorrect inputs for expression");
          }
          // removing first item from stack
          double intOne = (double)
                  ((Sexp) trackStack.pop()).accept(new ProcessSList(this.spreadSheet));

          // checking second item in expression is valid
          if ((!(trackStack.peek() instanceof SNumber))
                  && (!(trackStack.peek() instanceof SList))) {
            throw new IllegalArgumentException("Incorrect inputs for expression");
          }
          // removing first item from stack
          double intTwo = (double)
                  ((Sexp) trackStack.pop()).accept(new ProcessSList(this.spreadSheet));

          // pushing answer onto the stack
          trackStack.push(intOne < intTwo);
          break;
        case "APPEND":
          // too few items in expression
          if (trackStack.size() <= 1) {
            throw new IllegalArgumentException("Incorrect number of inputs for expression");
          }

          String retstr = "";
          // have to process everything within the stack
          while (trackStack.size() > 0) {
            // removing item from stack
            String item = ((Sexp)
                    trackStack.pop()).accept(new ProcessSList(this.spreadSheet)).toString();
            retstr = retstr + item;
          }
          trackStack.push(retstr);
          break;
        default:
          trackStack.push(l.get(i));
          break;
      }
    }
    return trackStack.pop();
  }

  // Given a string input referring to a cell, return the row (int form)
  private int getInputRow(String s) {
    int retint = 0;
    for (int i = 0; i < s.length(); i++) {
      if (!Character.isLetter(s.charAt(i))) {
        retint = Integer.parseInt(s.substring(i));
        return retint;
      }
    }
    return retint;
  }

  // Given a string input referring to a cell, return the column (int form)
  private int getInputColumn(String s) {
    String retstr = "";
    for (int i = 0; i < s.length(); i++) {
      if (!Character.isLetter(s.charAt(i))) {
        // if the current char isn't a letter, start checking for numbers
        return Coord.colNameToIndex(retstr);
      } else {
        // can replace with string builder
        retstr = retstr + s.charAt(i);
      }
    }
    return Coord.colNameToIndex(retstr);
  }
}
