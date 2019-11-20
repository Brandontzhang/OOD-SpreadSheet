package edu.cs3500.spreadsheets.view;

import java.io.IOException;

/**
 * Interface for SpreadSheetView.
 */

public interface ISpreadSheetView {
  /**
   * Renders the desired view that is implemented by the class.
   * @throws IOException when the input/output of the spreadsheet doesn't work.
   */
  void render() throws IOException;
}