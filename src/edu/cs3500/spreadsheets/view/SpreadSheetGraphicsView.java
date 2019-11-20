package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import edu.cs3500.spreadsheets.model.IWorkSheet;
import edu.cs3500.spreadsheets.view.panels.SpreadSheetPanel;
import edu.cs3500.spreadsheets.view.panels.SpreadSheetScrollPanel;


/**
 * Graphical view for spreadsheet.
 */

public class SpreadSheetGraphicsView extends JFrame implements ISpreadSheetView {
  // Consumer, accepts a string and performs an operation onit
  private Consumer<String> commandCallback;
  // Display view
  private SpreadSheetScrollPanel scroll;
  List<List<String>> data;

  /**
   * Constructor for Graphical view.
   * @param inputWs Worksheet
   */

  public SpreadSheetGraphicsView(IWorkSheet inputWs) {
    super();
    IWorkSheet ws = inputWs;
    this.data = ws.getProcessedDataSheet();

    this.setTitle("Spreadsheet");
    this.setSize(1002, 502);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //look into the different layouts
    this.setLayout(new BorderLayout());

    SpreadSheetPanel spreadSheetPanel = new SpreadSheetPanel(this.data);

    //Scroll bars
    JScrollBar horizontal = new JScrollBar(JScrollBar.HORIZONTAL);
    JScrollBar vertical = new JScrollBar(JScrollBar.VERTICAL, 0, 20, 0, 500);

    this.scroll = new SpreadSheetScrollPanel(spreadSheetPanel,
            horizontal, vertical);
    this.add(this.scroll, BorderLayout.CENTER);
    this.scroll.setPreferredSize(new Dimension(1002, 502));

    class HorizontalScrollListener implements AdjustmentListener {

      public void adjustmentValueChanged(AdjustmentEvent e) {
        scroll.horizontalScroll(e.getValue());
        scroll.repaint();
      }
    }

    class VerticalScrollListener implements AdjustmentListener {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        scroll.verticalScroll(e.getValue());
        scroll.repaint();
      }
    }

    horizontal.addAdjustmentListener(new HorizontalScrollListener());
    vertical.addAdjustmentListener(new VerticalScrollListener());


    this.add(vertical, BorderLayout.EAST);
    this.add(horizontal, BorderLayout.SOUTH);


    commandCallback = null;
    //this.pack();
  }

  @Override
  public void render() {
    this.pack();
    this.setVisible(true);
  }
}
