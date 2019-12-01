package edu.cs3500.spreadsheets.view.provider;

import java.awt.event.ActionListener;

import edu.cs3500.spreadsheets.view.ISpreadSheetView;

/**
 * Interface for any Spreadsheet view implementation.
 */
public interface SheetView /*extends ISpreadSheetView */{

  /**
   * All spreadsheets must be able to render themselves in some way.
   */
  void render();

  /**
   * Adds an ActionListener(controller) to respond to any actions that occur within this view.
   * @param listener the controller of this view
   */
  void setListener(ActionListener listener);
}
