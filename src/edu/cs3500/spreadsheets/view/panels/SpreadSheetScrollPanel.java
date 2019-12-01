package edu.cs3500.spreadsheets.view.panels;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 * Panel in charge of the scroll bars. Tracks their position and determines which cells to display.
 */
public class SpreadSheetScrollPanel extends JPanel {
  //  A second JPanel that has three fields that contain two JScrollBars and your prior grid JPanel.
  //  This panel needs to listen to any events coming from the scrollbars, and reposition your grid
  //  panel accordingly. You will also need to use a layout manager to position all three components
  //  properly; I have found the GridBagLayout to be the most useful here, and there are tutorials
  //  in the Oracle documentation on how to use them.

  JScrollBar horizontal;
  JScrollBar vertical;
  SpreadSheetPanel sp;

  /**
   * Constructor for a scroll panel to create references for the scroller bars and the panel
   * it wraps around.
   * @param sp Panel wrapped around
   * @param horizontal horizontal bar
   * @param vertical vertical bar
   */
  public SpreadSheetScrollPanel(SpreadSheetPanel sp, JScrollBar horizontal, JScrollBar vertical) {
    this.horizontal = horizontal;
    this.vertical = vertical;
    this.sp = sp;

    sp.add(horizontal);
  }

  @Override
  protected void paintComponent(Graphics g) {
    this.sp.setHeightWidth(this.getWidth(), this.getHeight());
    this.sp.paintComponent(g);
  }

  /**
   * Vertical scrolling.
   * @param scrollAmt amount to scroll.
   */

  public void verticalScroll(int scrollAmt) {
    if (scrollAmt >= this.vertical.getMaximum() - 100) {
      this.vertical.setMaximum(this.vertical.getMaximum() + 100);
    }
    this.sp.changeVerticalPosition(scrollAmt);
  }

  /**
   * Horizontal Scrolling.
   * @param scrollAmt amount to scroll.
   */

  public void horizontalScroll(int scrollAmt) {
    if (scrollAmt >= this.horizontal.getMaximum() - 100) {
      this.horizontal.setMaximum(this.horizontal.getMaximum() + 100);
    }
    this.sp.changeHorizontalPosition(scrollAmt);
  }
}
