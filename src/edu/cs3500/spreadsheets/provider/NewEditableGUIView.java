package edu.cs3500.spreadsheets.provider;

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.view.ISpreadSheetView;

/**
 * Class adapts the editable gui view provided to fit the original ISpreadSheetView interface.
 */
public class NewEditableGUIView implements ISpreadSheetView, SheetView {
  private EditableGUIView egv;

  public NewEditableGUIView(EditableGUIView egv) {
    this.egv = egv;
  }

  @Override
  public void getDisplayData(List<List<String>> data) {
    return;
  }

  @Override
  public void render() {
    this.egv.render();
  }

  @Override
  public void setListener(ActionListener listener) {
    this.egv.setListener(listener);
  }

  @Override
  public void setListener(EventListener listener) {
    this.egv.setListener((ActionListener) listener);
  }

  @Override
  public String getCoordCell(int x, int y) {
    return Coord.colIndexToName(this.egv.getCol() + 1) + (this.egv.getRow() + 1);
  }

  @Override
  public String getText() {
    return this.egv.getTextBar().getText();
  }

  @Override
  public void selectCell(int x, int y) {
    return;
  }

  @Override
  public void updateView(List<List<String>> data) {
    this.render();
  }

  @Override
  public void updateText(String text) {
    this.egv.getTextBar().setText(text);
  }
}
