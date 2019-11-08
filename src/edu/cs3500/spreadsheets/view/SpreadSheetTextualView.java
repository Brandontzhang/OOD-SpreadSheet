package edu.cs3500.spreadsheets.view;

import java.io.IOException;
import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ICell;
import edu.cs3500.spreadsheets.model.WorkSheet;

public class SpreadSheetTextualView implements ISpreadSheetView {

  private WorkSheet model;
  Appendable ap;

  public SpreadSheetTextualView(WorkSheet model) {
    this.model = model;
    this.ap = new StringBuilder();
  }

  public SpreadSheetTextualView(WorkSheet model, Appendable ap) throws IllegalArgumentException {
    if (ap == null) {
      throw new IllegalArgumentException("Null appendable");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void render() throws IOException {
    List<List<ICell>> displayData = this.model.getDataSheet();
    for (int i = 0; i < displayData.size(); i++) {
      for (int j = 0; j < displayData.get(i).size(); j++) {
        String col = Coord.colIndexToName(i + 1);
        String coord = col + (j + 1);
        if (!this.model.getCell(coord).equals("")) {
          // print out the contents
          String printContent = coord + ": " + this.model.getCell(coord);
          this.ap.append(printContent);
          this.ap.append("\n");
        }
      }
    }
  }

  @Override
  public String toString() {
    try {
      this.render();
      return this.ap.toString();
    } catch (IOException e) {
      System.out.println("I/O Error");
    }
    return "";
  }
}
