package edu.cs3500.spreadsheets.model;

/**
 * Builder class for Worksheets.
 */
public class WorksheetBuild implements WorksheetReader.WorksheetBuilder<WorkSheet> {
  private WorkSheet ws1;

  @Override
  public WorksheetReader.WorksheetBuilder<WorkSheet> createCell(int col, int row, String contents) {
    WorkSheet ws1 = new WorkSheet();
    String index = "" + Coord.colIndexToName(col) + row;
    ws1.updateCell(index, contents);
    return this;
  }

  @Override
  public WorkSheet createWorksheet() {
    WorkSheet ws1 = new WorkSheet();
    return ws1;
  }
}
