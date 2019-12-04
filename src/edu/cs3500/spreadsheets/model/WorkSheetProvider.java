package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.cs3500.spreadsheets.provider.Formula;
import edu.cs3500.spreadsheets.provider.IWorksheetProvider;

/**
 * Adapter class allowing the worksheet created to work with the provided worksheet interface.
 */
public class WorkSheetProvider implements IWorkSheet, IWorksheetProvider {
  private IWorkSheet myWS;

  public WorkSheetProvider(IWorkSheet ws) {
    this.myWS = ws;
  }

  // ISpreadSheet
  @Override
  public List<List<ICell>> getDataSheet() {
    return this.myWS.getDataSheet();
  }

  @Override
  public String getCell(String c) {
    return this.myWS.getCell(c);
  }

  @Override
  public void updateCell(String c, String s) {
    this.myWS.updateCell(c, s);
  }

  @Override
  public List<List<String>> getProcessedDataSheet() {
    return this.myWS.getProcessedDataSheet();
  }

  @Override
  public String getUnprocessedData(String c) {
    return this.myWS.getUnprocessedData(c);
  }

  // provided worksheet
  @Override
  public Cell getCellAt(Coord ref) {
    if (this.myWS.getAllCells().get(ref) == null) {
      return new Cell("");
    } else {
      return this.myWS.getAllCells().get(ref);
    }
  }

  @Override
  public List<Cell> getCells(Coord topLeft, Coord bottomRight) throws IllegalArgumentException {
    List<Cell> list = new ArrayList<>();
    for (int i = topLeft.col; i <= bottomRight.col; i++) {
      for (int j = topLeft.row; j <= bottomRight.row; j++) {
        Coord newCoord = new Coord(i, j);
        if (this.myWS.getAllCells().containsKey(newCoord)) {
          list.add(this.myWS.getAllCells().get(newCoord));
        }
      }
    }
    return list;
  }

  @Override
  public void editCell(Coord c, Formula newContents) {
    this.myWS.updateCell(c.toString(), newContents.toString());
  }

  @Override
  public HashMap<Coord, Cell> getAllCells() {
    return this.myWS.getAllCells();
  }

  @Override
  public HashMap<Coord, String> getAllProcessedCells() {
    return this.myWS.getAllProcessedCells();
  }

  @Override
  public Value evaluateCoord(Coord ref) {
    if (this.myWS.getAllCells().get(ref) == null) {
      return new Value("");
    } else {
      // return the processed content of the cell
      return new Value(this.myWS.getAllProcessedCells().get(ref));
    }
  }

  @Override
  // Didn't need to override this for view
  public Value evaluateCoord(Coord ref, HashMap<Cell, Value> checked) {
    return null;
  }
}
