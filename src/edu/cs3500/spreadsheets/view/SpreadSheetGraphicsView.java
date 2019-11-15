package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.IWorkSheet;


/**
 * Graphical view for spreadsheet.
 */

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

  /**
   * Constructor for Graphical view
   * @param ws Worksheet
   */

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
    this.horizontal = new JScrollBar(JScrollBar.HORIZONTAL);
    this.vertical = new JScrollBar(JScrollBar.VERTICAL, 0, 20, 0, 500);

    SpreadSheetScrollPanel scroll = new SpreadSheetScrollPanel(spreadSheetPanel,
            this.horizontal, this.vertical);
    this.add(scroll, BorderLayout.CENTER);
    scroll.setPreferredSize(new Dimension(1002, 502));

    class horizontalScrollListener implements AdjustmentListener {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        scroll.horizontalScroll(e.getValue());
        scroll.repaint();
      }
    }

    class verticalScrollListener implements AdjustmentListener {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        scroll.verticalScroll(e.getValue());
        scroll.repaint();
      }
    }

    this.horizontal.addAdjustmentListener(new horizontalScrollListener( ));
    this.vertical.addAdjustmentListener(new verticalScrollListener( ));


    this.add(this.vertical, BorderLayout.EAST);
    this.add(this.horizontal, BorderLayout.SOUTH);


    commandCallback = null;

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    //Nothing happening here
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
