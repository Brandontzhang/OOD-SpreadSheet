package edu.cs3500.spreadsheets.model;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for a Worksheet model. Supports operations for editing a worksheet, getting all the
 * cells, and evaluating coordinates.
 */
public interface IWorksheetProvider {

  /**
   * Returns the Cell at the given Coord.
   * @param ref the reference coordinate for the Cell to return
   * @return the Cell at the given Coord
   */
  Cell getCellAt(Coord ref);

  /**
   * Gets a list of Cells from the top left to the bottom right coordinates.
   * @param topLeft Top left coordinate to start from
   * @param bottomRight Bottom right coordinate to finish from
   * @return A list of Cells in the region outlined from the given Coords
   * @throws IllegalArgumentException if topLeft is not before bottomRight
   */
  List<Cell> getCells(Coord topLeft, Coord bottomRight) throws IllegalArgumentException;

  /**
   * Edit a cell in this model Worksheet.
   * @param c the Coord to reference for Cell
   * @param newContents the new contents of the Cell at the given Coord
   */
  void editCell(Coord c, Formula newContents);

  /**
   * Returns a HashMap of all Cells in the Worksheet.
   * @return the HashMap of all Cells
   */
  HashMap<Coord, Cell> getAllCells();

  /**
   * Evaluate the Cell at the given Coord.
   * @param ref the reference Coord
   * @return the Value that results from evaluating the Cell at the given Coord
   */
  Value evaluateCoord(Coord ref);

  /**
   * Evaluate the Cell at the given Coord. Use this method when for more efficacious results
   * after having evaluating some cells as part of a single formula, which should be stored
   * in the cache.
   * @param ref the reference Coord
   * @param checked cached results
   * @return the Value that results from evaluating the Cell at the given Coord
   */
  Value evaluateCoord(Coord ref, HashMap<Cell, Value> checked);
}
