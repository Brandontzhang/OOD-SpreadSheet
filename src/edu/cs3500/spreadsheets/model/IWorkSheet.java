package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Interface for a worksheet.
 */
public interface IWorkSheet {
  // Returns the contents of the cell given the coordinates
  String getCell(String c);

  // update a cell
  void updateCell(String c, String s);

  // Method to return the entire data set
  List<List<ICell>> getDataSheet();
}
