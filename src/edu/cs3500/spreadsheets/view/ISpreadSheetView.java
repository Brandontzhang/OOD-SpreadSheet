package edu.cs3500.spreadsheets.view;

import java.io.IOException;
import java.util.EventListener;
import java.util.List;

/**
 * Represents a view to be used with a spreadsheet. Interface has methods used by all views,
 * specific methods for individual views (mostly editing), throw unsupported exceptions in
 * implementation.
 */
public interface ISpreadSheetView {
  /**
   * gets the view a set of data to display.
   */
  void getDisplayData(List<List<String>> data);

  /**
   * Renders the desired view that is implemented by the class.
   * @throws IOException when the input/output of the spreadsheet doesn't work.
   */

  void render() throws IOException;

  /**
   * sets the listener for the components in the view.
   * @param listener controller of the view.
   */
  void setListener(EventListener listener);

  /**
   * gets the corresponding cell coordinate on the view to a coordinate x, y.
   * @param x x-coordinate of cell on the view.
   * @param y y-coordinate of cell on the view.
   */
  String getCoordCell(int x, int y);

  /**
   * get the text to update cell with from the text box.
   */
  String getText();

  /**
   * selects the cell at the given coordinate.
   */
  void selectCell(int x, int y);

  /**
   * update the view with new data to be displayed.
   */
  void updateView(List<List<String>> data);

  /**
   * updates the textbox with new data.
   */

  void updateText(String text);
}
