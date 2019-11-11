package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.ICell;
import edu.cs3500.spreadsheets.model.IWorkSheet;


public class SpreadSheetGraphicsView extends JFrame implements IView {
  //worksheet
  private IWorkSheet ws;

  // buttons needed for the interface
  private JButton testButton;

  // panels... not sure what these do yet, holding stuff?
  private JPanel buttonPanel;

  // class used to specify drawing data
  private SpreadSheetPanel spreadSheetPanel;

  // scrolling
  private JScrollPane scrollPane;
  private JScrollBar horizontal;
  private JScrollBar vertical;

  // text fields needed for the interface
  private JTextField input;

  // Consumer, accepts a string and performs an operation onit
  Consumer<String> commandCallback;

  public SpreadSheetGraphicsView(IWorkSheet ws) {
    super();
    this.ws = ws;
    List<List<String>> data = ws.getProcessedDataSheet();

    this.setTitle("Spreadsheet");
    this.setSize(1002, 502);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //look into the different layouts
    this.setLayout(new BorderLayout());

    spreadSheetPanel = new SpreadSheetPanel(data);

    //Scoll bars
    this.horizontal = new JScrollBar(JScrollBar.HORIZONTAL, 0, 20, 0, 500);
    this.vertical = new JScrollBar(JScrollBar.VERTICAL, 0, 20, 0, 500);

    SpreadSheetScrollPanel scroll = new SpreadSheetScrollPanel(spreadSheetPanel, this.horizontal, this.vertical);
    this.add(scroll, BorderLayout.CENTER);
    scroll.setPreferredSize(new Dimension(1002, 502));


    this.add(this.vertical, BorderLayout.EAST);
    this.add(this.horizontal, BorderLayout.SOUTH);

//    this.add(this.horizontal, BorderLayout.SOUTH);
//    this.add(this.vertical, BorderLayout.EAST);

//    scrollPane = new JScrollPane(spreadSheetPanel);
//    this.add(scrollPane, BorderLayout.CENTER);

//    //button panel
//    buttonPanel = new JPanel();
//    buttonPanel.setLayout(new FlowLayout());
//    this.add(buttonPanel, BorderLayout.SOUTH);
//
//    //input textfield
//    input = new JTextField(15);
//    buttonPanel.add(input);
//
//    //buttons
//    testButton = new JButton("Execute");
//    testButton.addActionListener((ActionEvent e) ->
//    {
//      if (commandCallback != null) { //if there is a command callback
//        commandCallback.accept(input.getText()); //send command to be processed
//        input.setText(""); //clear the input text field
//      }
//    });
//    buttonPanel.add(testButton);

//    //another button
//    quitButton = new JButton("Quit");
//    quitButton.addActionListener((ActionEvent e) -> {
//      System.exit(0);
//    });
//    buttonPanel.add(quitButton);

    commandCallback = null;

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {

  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);

  }
}
