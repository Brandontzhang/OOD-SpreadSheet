package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.IWorkSheet;

public class SpreadSheetEditView extends JFrame implements ISpreadSheetView {
  private SpreadSheetGraphicsView graphicView;
  private JPanel buttonPanel;
  private JButton enterButton;
  private JTextField input;

  public SpreadSheetEditView(IWorkSheet inputWs) {
    this.graphicView = new SpreadSheetGraphicsView(inputWs);
    //this.graphicView.getContentPane();
    this.add(this.graphicView.getContentPane());

    this.setTitle("Editing");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    enterButton = new JButton("Enter");
    buttonPanel.add(enterButton);

    //input textfield
    input = new JTextField(15);
    buttonPanel.add(input);

    this.buttonPanel.setPreferredSize(new Dimension(1002, 35));

    this.pack();
  }

  @Override
  public void render() throws IOException {
      this.setVisible(true);
  }
}
