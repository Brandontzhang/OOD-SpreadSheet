package edu.cs3500.spreadsheets.provider;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * An editable version of the GUI view that allows for user manipulation of the spreadsheet
 * through keyboard, click, and button commands.
 */
public class EditableGUIView extends JFrame implements SheetView, ChangeListener {

  private IWorksheetProvider model;
  private SheetPanel panel;
  private JButton cancelButton;
  private JButton confirmButton;
  private JButton saveButton;
  private JButton loadButton;
  private JTextField formulaBar;
  private GridBagConstraints constraints;

  /**
   * Constructs an editable GUI view based on the given model.
   * @param model the model to be viewed
   */
  public EditableGUIView(IWorksheetProvider model) {
    super("SpreadSheet");
    this.model = model;

    // Set basic JFrame params
    this.setSize(1000, 1000);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setMinimumSize(new Dimension(300, 300));
    this.setMaximumSize(new Dimension(300, 300));

    // Setting layout features for JTable representing
    this.setLayout(new GridBagLayout());

    this.panel = new SheetPanel(model);

    this.constraints = new GridBagConstraints();
    this.cancelButton = new JButton("Cancel");
    this.confirmButton = new JButton("Confirm");
    this.saveButton = new JButton("Save");
    this.loadButton = new JButton("Load");
    this.formulaBar = new JTextField("");

    // Essential frame commands
    this.constraints.fill = GridBagConstraints.HORIZONTAL;
    this.add(this.formulaBar, this.constraints);
    this.constraints.gridy = 1;
    this.add(this.panel.getScrollPane(), this.constraints);
    this.constraints.gridy = 0;
    this.constraints.gridx = 1;
    this.add(this.confirmButton, this.constraints);
    this.constraints.gridx = 2;
    this.add(this.cancelButton, this.constraints);
    this.constraints.gridx = 3;
    this.add(this.saveButton, this.constraints);
    this.constraints.gridx = 4;
    this.add(this.loadButton, this.constraints);

    pack();
    setVisible(true);

    this.panel.getScrollPane().getViewport().addChangeListener(this);
    this.panel.setSelectionListener(this.formulaBar);
  }

  @Override
  public void render() {
    this.panel.updateCells();
  }

  @Override
  public void setListener(ActionListener listener) {
    this.cancelButton.addActionListener(listener);
    this.confirmButton.addActionListener(listener);
    this.saveButton.addActionListener(listener);
    this.loadButton.addActionListener(listener);
    this.formulaBar.addActionListener(listener);
  }

  public JTextField getTextBar() {
    return this.formulaBar;
  }

  public int getRow() {
    return this.panel.getRow();
  }

  public int getCol() {
    return this.panel.getCol();
  }

  @Override
  public void stateChanged(ChangeEvent changeEvent) {
    Point p = this.panel.getScrollPane().getViewport().getViewPosition();
    int rowNum = p.y / 16;
    int colNum = p.x / 72;
    this.panel.doubleColumns(colNum);
    this.panel.doubleRows(rowNum);
  }
}