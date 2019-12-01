package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import edu.cs3500.spreadsheets.view.panels.SpreadSheetPanel;
import edu.cs3500.spreadsheets.view.panels.SpreadSheetScrollPanel;


/**
 * Graphical view for the spread sheet. Only handles displaying the data from the model.
 */

public class SpreadSheetGraphicsView extends JFrame implements ISpreadSheetView {
  // Display view
  private SpreadSheetScrollPanel scroll;
  private List<List<String>> data;
  private SpreadSheetPanel spreadSheetPanel;
  private JScrollBar horizontal;
  private JScrollBar vertical;

  /**
   * Constructor for Graphical view.
   */

  public SpreadSheetGraphicsView() {
    super();
    this.data = new ArrayList<>();

    this.setTitle("Spreadsheet");
    this.setSize(1002, 502);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //look into the different layouts
    this.setLayout(new BorderLayout());

    this.spreadSheetPanel = new SpreadSheetPanel(this.data);

    //Scroll bars
    this.horizontal = new JScrollBar(JScrollBar.HORIZONTAL);
    this.vertical = new JScrollBar(JScrollBar.VERTICAL, 0, 20, 0, 500);

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
  }

  @Override
  public void getDisplayData(List<List<String>> data) {
    this.data = data;
    this.updateView(data);
  }

  @Override
  public void render() {
    this.pack();
    this.scroll.repaint();
    this.setVisible(true);
  }

  @Override
  public void setListener(EventListener listener) {
    this.scroll.addMouseListener((MouseListener) listener);
  }

  @Override
  public String getCoordCell(int x, int y) {
    return this.spreadSheetPanel.getCellName(x, y);
  }

  @Override
  public String getText() {
    return "";
  }

  @Override
  public void selectCell(int x, int y) {
    this.spreadSheetPanel.setSelectedCell(x, y);
    this.scroll.repaint();
  }

  @Override
  public void updateView(List<List<String>> data) {
    this.data = data;
    this.spreadSheetPanel.updateData(data);
    this.scroll.repaint();
  }

  @Override
  public void updateText(String text) {
    return;
  }

  @Override
  public int getHeight() {
    return this.scroll.getHeight();
  }
}
