package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.visitors.AppendVisitor;
import edu.cs3500.spreadsheets.sexp.visitors.GetSListContent;
import edu.cs3500.spreadsheets.sexp.visitors.LessThanVisitor;
import edu.cs3500.spreadsheets.sexp.visitors.ProductVisitor;
import edu.cs3500.spreadsheets.sexp.visitors.SubVisitor;
import edu.cs3500.spreadsheets.sexp.visitors.SumVisitor;


/**
 * Worksheet class.
 */
public class WorkSheet implements IWorkSheet {
  // 2D arraylist of cells
  private List<List<ICell>> spreadSheet;
  private int spreadSheetSize = 10;

  /**
   * Represents a worksheet.
   */
  public WorkSheet() {
    // for inputs out of bounds, make a loop from current size of spreadsheet up to input and insert
    // null in them until we get to the cell
    this.spreadSheet = new ArrayList<>(spreadSheetSize);

    // setting initial spreadsheet to empty cells (100 x 100 cells)
    for (int i = 0; i < spreadSheetSize; i++) {
      this.spreadSheet.add(new ArrayList<>(spreadSheetSize));
      for (int j = 0; j < spreadSheetSize; j++) {
        this.spreadSheet.get(i).add(j, new Cell());
      }
    }
  }

  // Cyclic checks should be done when updating cell, not evaluating
  @Override
  public void updateCell(String c, String s) {
    Parser p = new Parser();
    if (p.parse(s) instanceof SSymbol) {
      if (validCellAddress(s)) {
        int rowS = this.getInputRow(s);
        int colS = this.getInputColumn(s);

        if (this.cyclicCheck(this.spreadSheet.get(colS).get(rowS).getContent(), c)) {
          throw new IllegalArgumentException("Cycle detected");
        }
      }
    }

    int row = this.getInputRow(c);
    int col = this.getInputColumn(c);

    if (col > this.spreadSheet.size() - 1) {
      this.increaseSize(col, row);
    } else if (row > this.spreadSheet.get(col).size() - 1) {
      this.increaseSize(col, row);
    }

    try {
      processCell(p.parse(s));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Badly formatted expression");
    }

    this.spreadSheet.get(col).get(row).updateCell(s);
  }

  // basically going through all the sexpressions evaluated and seeing if the same cell is referenced twice
  private boolean cyclicCheck(Sexp check, String cellCoord) {
    int row = this.getInputRow(cellCoord);
    int col = this.getInputColumn(cellCoord);
    if (check == null) {
      return false;
    }else if (check instanceof SSymbol) {
      if (validCellAddress(check.toString())) {
        Sexp s = this.spreadSheet.get(col).get(row).getContent();
        return (check.toString().equals(cellCoord)) || cyclicCheck(s, cellCoord);
      } else {
        return false;
      }
    } else if (check instanceof SList) {
      GetSListContent getList = new GetSListContent();
      ArrayList<Sexp> processList = (ArrayList<Sexp>) check.accept(getList);
      boolean accBool = false;
      for (Sexp s : processList) {
        accBool = accBool || cyclicCheck(s, cellCoord);
      }
      return accBool;
    } else {
     return false;
    }
  }

  @Override
  // should return a string holding the content
  public String getCell(String in) {
    // Creating a coordinate to work with
    int row = this.getInputRow(in);
    int col = this.getInputColumn(in);
    // Check if the coordinate is out of bounds for current array list
    if ((col >= this.spreadSheetSize) || (row >= this.spreadSheetSize)) {
      // Column is out of bounds, create a larger array list
      this.increaseSize(row, col);
    }
    // return content of cell
    try {
      return this.processCell(spreadSheet.get(col).get(row).getContent()).toString();
    } catch (NullPointerException e) {
      return "";
    }
  }

  // processes the contents of a cell and returns the content as a string
  private Sexp processCell(Sexp s) {
    if (s instanceof SSymbol) {
      // Single cell reference
      // determine if symbol is referencing a cell
      if (validCellAddress(s.toString())) {
        // get the Sexp of the cell and return its content after it is processed
        int col = getInputColumn(s.toString());
        int row = getInputRow(s.toString());
        return this.processCell(this.spreadSheet.get(col).get(row).getContent());
      } else {
        // not a cell reference
        return s;
      }
    } else if (s instanceof SList) {
      // have a visitor return a list of Sexp to parse
      // Process the list backwards with a <Sexp>stack
      Stack<Sexp> evaluate = new Stack<>();
      GetSListContent getList = new GetSListContent();
      ArrayList<Sexp> processList = (ArrayList<Sexp>) s.accept(getList);
      Sexp temp;
      for (int i = processList.size() - 1; i >= 0; i--) {
        temp = processList.get(i);
        if (temp instanceof SSymbol) {
          // check if it is a cell reference or a method call (ex. SUM)
          if (validCellAddress(temp.toString())) {
            // Singular cell reference
            // push the single cells processCell(cell.Sexp) to the stack (saves the Sexp)
            int col = this.getInputColumn(temp.toString());
            int row = this.getInputRow(temp.toString());
            evaluate.push(this.processCell(this.spreadSheet.get(col).get(row).getContent()));
          } else if (temp.toString().matches("(.*):(.*)")) {
            // Multiple cell reference
            // push multiple cells processCell(cell.Sexp) Cell to the stack
            String coord1 = temp.toString().substring(0, 2);
            String coord2 = temp.toString().substring(3, 5);
            int col1 = this.getInputColumn(coord1);
            int row1 = this.getInputRow(coord1);
            int col2 = this.getInputColumn(coord2);
            int row2 = this.getInputRow(coord2);
            if (col1 == col2) {
              //iterate through the rows
              for (int t = row1; t <= row2; t++) {
                evaluate.push(this.spreadSheet.get(col1).get(t).getContent());
              }
            } else if (row1 == row2) {
              //iterate through the columns
              for (int t = col1; t <= col2; t++) {
                evaluate.push(this.spreadSheet.get(t).get(row1).getContent());
              }
            } else {
              throw new IllegalArgumentException("Incorrect cell formatting");
            }
          } else {
            // method Call
            // build a new SList with the items in the stack
            List<Sexp> parts = new ArrayList<>();
            while (evaluate.size() > 0) {
              if (evaluate.peek() instanceof SList) {
                evaluate.push(this.processCell(evaluate.pop()));
              }
              parts.add(evaluate.pop());
            }
            SList tempSList = new SList(parts);

            // switch statement with corresponding methods
            switch (temp.toString()) {
              // call the corresponding visitor to the method call
              case "PRODUCT":
                ProductVisitor prod = new ProductVisitor();
                evaluate.push(tempSList.accept(prod));
                break;
              case "SUM":
                SumVisitor sum = new SumVisitor();
                evaluate.push(tempSList.accept(sum));
                break;
              case "SUB":
                SubVisitor sub = new SubVisitor();
                evaluate.push(tempSList.accept(sub));
                break;
              case "<":
                LessThanVisitor greater = new LessThanVisitor();
                evaluate.push(tempSList.accept(greater));
                break;
              case "APPEND":
                AppendVisitor append = new AppendVisitor();
                evaluate.push(tempSList.accept(append));
                break;
              default:
                // error if nothing matches?
            }
          }
        } else {
          // push Sexp to stack
          evaluate.push(processList.get(i));
        }
      }
      // there should only be one item left in the stack at the end
      return evaluate.pop();
    }
    return s;
  }

  // increasing the spreadsheet arraylist to size of input
  private void increaseSize(int row, int col) {
    int colSize = this.spreadSheet.size();
    for (int i = 0; i <= col - colSize; i++) {
      this.spreadSheet.add(new ArrayList<>(this.spreadSheet.get(0).size()));
    }
    for (List<ICell> iCells : this.spreadSheet) {
      int rowSize = iCells.size();
      for (int j = 0; j <= row - rowSize; j++) {
        iCells.add(new Cell());
      }
    }
  }

  // Given a string input referring to a cell, return the column (int form)
  private int getInputColumn(String s) {
    if (validCellAddress(s)) {
      //String retstr = "";
      StringBuilder retstr = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
        if (!Character.isLetter(s.charAt(i))) {
          // if the current char isn't a letter, start checking for numbers
          return Coord.colNameToIndex(retstr.toString());
        } else {
          // can replace with string builder
          retstr.append(s.charAt(i));
        }
      }
      return Coord.colNameToIndex(retstr.toString());
    } else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  // Given a string input referring to a cell, return the row (int form)
  private int getInputRow(String s) {
    if (validCellAddress(s)) {
      int retint = 0;
      for (int i = 0; i < s.length(); i++) {
        if (!Character.isLetter(s.charAt(i))) {
          retint = Integer.parseInt(s.substring(i));
          return retint;
        }
      }
      return retint;
    } else {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  public static boolean validCellAddress(String s) {
    boolean lookForNum = false;
    boolean lookForLetter = true;
    boolean foundNum = false;
    boolean foundLetter = false;
    // check for letters at the start of the string
    for (int i = 0; i < s.length(); i++) {
      if (lookForLetter) {
        if (!Character.isLetter(s.charAt(i))) {
          if (!Character.isDigit(s.charAt(i))) {
            return false;
          } else {
            lookForLetter = false;
            lookForNum = true;
          }
        } else {
          foundLetter = true;
        }
      }

      if (lookForNum) {
        if(!Character.isDigit(s.charAt(i))) {
          return false;
        } else {
          foundNum = true;
        }
      }
    }
    return foundLetter && foundNum;
  }

  @Override
  public List<List<ICell>> getDataSheet() {
    // copies the spreadsheet to return it
    List<List<ICell>> dataCopy = new ArrayList<>();
    for (int i = 0; i < this.spreadSheet.size(); i++){
      dataCopy.add(new ArrayList<>());
      for (int j = 0; j < this.spreadSheet.get(i).size(); j++) {
        dataCopy.get(i).add(this.spreadSheet.get(i).get(j));
      }
    }
    return dataCopy;
  }

  @Override
  public List<List<String>> getProcessedDataSheet() {
    // copies the spreadsheet to return it
    List<List<String>> dataCopy = new ArrayList<>();
    for (int i = 0; i < this.spreadSheet.size(); i++){
      dataCopy.add(new ArrayList<String>());
      for (int j = 0; j < this.spreadSheet.get(i).size(); j++) {
        if (i != 0 && j != 0) {
          String cell = "" + Coord.colIndexToName(i) + j;
          dataCopy.get(i).add(this.getCell(cell));
        }
      }
    }
    return dataCopy;
  }
}
