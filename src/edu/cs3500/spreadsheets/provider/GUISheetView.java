package edu.cs3500.spreadsheets.provider;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * GUI View implementation for Spreadsheets.
 */
public class GUISheetView extends JFrame implements SheetView {

  private IWorksheetProvider model;

  /**
   * Constructor for GUIView.
   * @param model The model of the view
   */
  public GUISheetView(IWorksheetProvider model) {
    super("SpreadSheet");
    this.model = model;

    // Set basic JFrame params
    this.setSize(1000, 1000);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setMinimumSize(new Dimension(300, 300));
    this.setMaximumSize(new Dimension(300, 300));

    // Setting layout features for JTable representing
    this.setLayout(new FlowLayout());

    SheetPanel panel = new SheetPanel(model);

    // Essential frame commands
    this.add(panel.getScrollPane());

    pack();
    setVisible(true);
  }

  @Override
  public void render() {
    // The GUI view does not yet need to ever update, so the render method is not used
  }

  @Override
  public void setListener(ActionListener listener) {
    throw new UnsupportedOperationException("No actions for static GUI View");
  }
}