package edu.cs3500.spreadsheets.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;


/**
 * Textual View for SpreadSheet, returns the data held by a spreadsheet.
 */

public class SpreadSheetTextualView implements ISpreadSheetView {

  private List<List<String>> displayData;
  Appendable ap;

  /**
   * Constructor for spread sheet textual view.
   */
  public SpreadSheetTextualView() {
    this.displayData = new ArrayList<>();
    this.ap = new StringBuilder();
  }

  @Override
  public void getDisplayData(List<List<String>> data) {
    this.displayData = data;
  }

  @Override
  public void render() throws IOException {
    for (int i = 0; i < this.displayData.size() - 1; i++) {
      for (int j = 0; j < this.displayData.get(i).size() - 1; j++) {
        String col = Coord.colIndexToName(i + 1);
        String coord = col + (j + 1);
        if (!displayData.get(i).get(j).equals("")) {
          // print out the contents
          String printContent = coord + " =" + displayData.get(i).get(j);
          this.ap.append(printContent);
          this.ap.append("\n");
        }
      }
    }
  }

  @Override
  public String toString() {
    return this.ap.toString();
  }

  @Override
  public void setListener(EventListener listener) {
    return;
  }

  @Override
  public String getCoordCell(int x, int y) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getText() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void selectCell(int x, int y) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void updateView(List<List<String>> data) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void updateText(String text) {
    throw new UnsupportedOperationException();
  }
}
