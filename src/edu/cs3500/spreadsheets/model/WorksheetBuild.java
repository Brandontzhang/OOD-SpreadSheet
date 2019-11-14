package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Builder class for Worksheets.
 */
public class WorksheetBuild implements WorksheetReader.WorksheetBuilder<WorkSheet> {
  private WorkSheet ws1;
  private HashMap<String, Sexp> buildCells = new HashMap<>();

  @Override
  public WorksheetReader.WorksheetBuilder<WorkSheet> createCell(int col, int row, String contents) {
    String index = "" + Coord.colIndexToName(col) + row;
    Parser p = new Parser();
    contents = contents.substring(1);
    System.out.println(contents);
    Sexp s = p.parse(contents);
    buildCells.put(index, s);
    return this;
  }

  @Override
  public WorkSheet createWorksheet() {
    WorkSheet ws1 = new WorkSheet();
    buildCells.forEach((k, v) -> ws1.updateCell(k, v.toString()));
    return ws1;
  }
}
