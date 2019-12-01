package edu.cs3500.spreadsheets.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import edu.cs3500.spreadsheets.model.ISpreadSheet;
import edu.cs3500.spreadsheets.model.IWorkSheet;
import edu.cs3500.spreadsheets.view.ISpreadSheetView;

/**
 * Controller class for SpreadSheets. Is an ActionListener and MouseListener to get input from the
 * view, which it then translates into actual calls the the worksheet model. When updates are made,
 * it then takes information from the worksheet model and gives it to the view to display.
 */
public class WorkSheetController implements ActionListener, MouseListener {
  public ISpreadSheet model;
  public ISpreadSheetView view;
  // string of cell selected in the view, also add so that text field always display selected
  String selected;

  /**
   * Controller monitoring model and view.
   * @param model input model.
   * @param view input view.
   */
  public WorkSheetController(ISpreadSheet model, ISpreadSheetView view) {
    this.model = model;
    this.view = view;
    this.view.getDisplayData(this.model.getProcessedDataSheet());
    this.selected = "A1";
    view.setListener(this);
    try {
      view.render();
    } catch (IOException e) {
      System.out.println("I/O error");
    }
  }

  @Override
  // click
  public void mouseClicked(MouseEvent e) {
    Point p = e.getLocationOnScreen();
    // from the view, get the corresponding coordinate of that cell
    String coord = this.view.getCoordCell(p.x, p.y);
    if (coord == null) {
      return;
    }
    if (coord.equals("edit") || coord.equals("scroll")) {
      // user clicked on scroll/edit areas
      return;
    } else {
      // select the cell in the controller
      this.selected = coord;
      // select the cell in the view
      this.view.selectCell(p.x, p.y);
      // sets the text to match the contents of the cell
      this.view.updateText(this.model.getUnprocessedData(this.selected));
    }
  }

  @Override
  // press (not released)
  public void mousePressed(MouseEvent e) {
    // no mouse pressed events
    return;
  }

  @Override
  // release
  public void mouseReleased(MouseEvent e) {
    // no mouse released events
    return;
  }

  @Override
  // enter a component
  public void mouseEntered(MouseEvent e) {
    // no mouse entered events
    return;
  }

  @Override
  // exit a component
  public void mouseExited(MouseEvent e) {
    // no mouse exit events
    return;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch ((e.getActionCommand())) {
      case "Confirm":
        // change the contents of the current selected cell in the model to the input in text field
        this.model.updateCell(this.selected, this.view.getText());
        this.view.updateView(this.model.getProcessedDataSheet());
        break;
      case "Reject":
        // revert the text box back to the old stuff
        this.view.updateText(this.model.getUnprocessedData(this.selected));
        break;
      case "Save":
        break;
      case "Load":
        break;
    }
  }

  // Renders the view the controller is given
  public void render() {
    try {
      this.view.render();
    } catch (IOException e) {
      System.out.println("IO err");
    }
  }
}
