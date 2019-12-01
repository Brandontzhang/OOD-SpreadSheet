package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    return this.myWS.getAllCells().get(ref);
  }

  @Override
  public List<Cell> getCells(Coord topLeft, Coord bottomRight) throws IllegalArgumentException {
    List<Cell> list = new ArrayList<>();
    for (int i = topLeft.col; i <= bottomRight.col; i++) {
      for (int j = topLeft.row; j <- bottomRight.row; j++) {
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
  public Value evaluateCoord(Coord ref) {
    //return new Value(this.myWS.getAllCells().get(ref).getUnevalContent());
    return null;
  }

  @Override
  public Value evaluateCoord(Coord ref, HashMap<Cell, Value> checked) {
    return null;
  }
}
