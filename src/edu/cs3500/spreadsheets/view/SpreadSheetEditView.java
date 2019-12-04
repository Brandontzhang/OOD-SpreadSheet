package edu.cs3500.spreadsheets.view;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.EventListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;


/**
 * The editing view for spreadsheets. It takes a graphical view and adds a few new components on top
 * of it which enable editing. It sends data to whichever class it set as its listener, and updates
 * itself according to the information it receives back.
 */

public class SpreadSheetEditView extends JFrame implements ISpreadSheetView {
  private SpreadSheetGraphicsView graphicView;
  private JPanel buttonPanel;
  private JButton confirmButton;
  private JButton rejectButton;
  private JTextField input;
  private List<List<String>> data;

  /**
   * Constructor for an edit view.
   */
  public SpreadSheetEditView() {
    this.graphicView = new SpreadSheetGraphicsView();
    this.add(this.graphicView.getContentPane());

    this.setTitle("Editing");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    confirmButton = new JButton("Enter");
    confirmButton.setActionCommand("Enter");
    buttonPanel.add(confirmButton);

    rejectButton = new JButton("Reject");
    rejectButton.setActionCommand("Reject");
    buttonPanel.add(rejectButton);

    //input textfield
    input = new JTextField(15);
    buttonPanel.add(input);

    this.buttonPanel.setPreferredSize(new Dimension(1002, 35));

    this.pack();
  }

  @Override
  public void getDisplayData(List<List<String>> data) {
    this.data = data;
    this.graphicView.getDisplayData(this.data);
  }

  @Override
  public void render() throws IOException {
    this.setVisible(true);
  }

  @Override
  public void setListener(EventListener listener) {
    if (!(listener instanceof ActionListener) && (listener instanceof MouseListener)) {
      throw new IllegalArgumentException("controller has to listen to both mouse and actions");
    }
    confirmButton.addActionListener((ActionListener) listener);
    rejectButton.addActionListener((ActionListener) listener);

    // panels should add mouse listener
    buttonPanel.addMouseListener((MouseListener) listener);

    // graphic view components also need to listen
    this.graphicView.setListener(listener);
  }

  @Override
  public String getCoordCell(int x, int y) {
    int xDim = this.buttonPanel.getWidth();
    int yDim = this.buttonPanel.getHeight() + this.graphicView.getHeight();
    // check for clicking on scroll bar
    if (x > xDim - 5) {
      return "scroll";
    }
    // check for clicking on edit bar
    if (y > yDim) {
      return "edit";
    }
    String coord = this.graphicView.getCoordCell(x, y);
    return coord;
  }

  @Override
  public String getText() {
    return this.input.getText();
  }

  @Override
  public void selectCell(int x, int y) {
    this.graphicView.selectCell(x, y);
  }

  @Override
  public void updateView(List<List<String>> data) {
    this.graphicView.updateView(data);
  }

  @Override
  public void updateText(String text) {
    this.input.setText(text);
    this.buttonPanel.repaint();
  }
}
