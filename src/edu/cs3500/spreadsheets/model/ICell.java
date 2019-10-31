package edu.cs3500.spreadsheets.model;


/**
 * Interface for a cell.
 */
public interface ICell {
  // update the cell
  void updateCell(String s);

  // view the contents of the cell
  String viewCell();
}
