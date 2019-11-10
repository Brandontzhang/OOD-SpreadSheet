package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.*;


public class SpreadSheetGraphicsView extends JFrame implements IView {
  // buttons needed for the interface
  private JButton testButton;

  // panels... not sure what these do yet, holding stuff?
  private JPanel buttonPanel;

  // class used to specify drawing data
  private SpreadSheetPanel spreadSheetPanel;

  // scrolling
  private JScrollPane scrollPane;

  // text fields needed for the interface
  private JTextField input;

  // Consumer, accepts a string and performs an operation onit
  Consumer<String> commandCallback;

  public SpreadSheetGraphicsView() {
    super();
    this.setTitle("Turtles!");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //look into the different layouts
    this.setLayout(new BorderLayout());

    spreadSheetPanel = new SpreadSheetPanel();
    spreadSheetPanel.setPreferredSize(new Dimension(500, 500));
    scrollPane = new JScrollPane(spreadSheetPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //input textfield
    input = new JTextField(15);
    buttonPanel.add(input);

    //buttons
    testButton = new JButton("Execute");
    testButton.addActionListener((ActionEvent e) ->
    {
      if (commandCallback != null) { //if there is a command callback
        commandCallback.accept(input.getText()); //send command to be processed
        input.setText(""); //clear the input text field
      }
    });
    buttonPanel.add(testButton);

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

  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {

  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void refresh() {

  }
}
