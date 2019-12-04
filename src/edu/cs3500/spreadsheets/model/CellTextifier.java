package edu.cs3500.spreadsheets.model;

/**
 * Visitor class (only one needed) needed for provider view implementation. We didn't need it to
 * process the cell, so we just returned the string of the cell.
 */
public class CellTextifier {
  public String visitCell(Cell cell) {
    return cell.getUnevalContent();
  }
}
