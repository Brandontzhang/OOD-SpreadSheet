package edu.cs3500.spreadsheets.model;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a worksheet. Data is held in a 2D list of cells.
 */
public interface IWorkSheet extends ISpreadSheet {
  // Method to return the entire data set
  List<List<ICell>> getDataSheet();

  // Returns the contents of the cell given the coordinates
  String getCell(String c);

  // Returns a hashmap of all cells
  HashMap<Coord, Cell> getAllCells();

  // Returns a hashmap of all cells with content processed
  HashMap<Coord, String> getAllProcessedCells();
}