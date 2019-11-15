package edu.cs3500.spreadsheets.view;

import java.util.function.Consumer;

/**
 * Interface for View.
 */
public interface IView {
  /**
   * Make the view visible. This is usually called after the view is constructed.
   */
  void makeVisible();

  /**
   * Provide the view with a callback option to process a command.
   *
   * @param callback object
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error error message to be sent.
   */
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself.
   */
  void refresh();
}
