package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * read only interface for spreadsheets, extended by IWorksheet.
 */
public interface ISpreadSheet {
  // Method to return the entire processed data set
  List<List<String>> getProcessedDataSheet();

  // return the unprocessed contents of the cell given the coordinates
  String getUnprocessedData(String c);

  // update a cell
  void updateCell(String c, String s);
}
