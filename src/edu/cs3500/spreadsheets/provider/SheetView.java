package edu.cs3500.spreadsheets.provider;

import java.awt.event.ActionListener;

/**
 * Interface for any Spreadsheet view implementation.
 */
public interface SheetView {

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
