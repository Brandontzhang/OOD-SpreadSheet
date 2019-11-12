package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;

public class SpreadSheetScrollPanel extends JPanel {
//  A second JPanel that has three fields that contain two JScrollBars and your prior grid JPanel.
//  This panel needs to listen to any events coming from the scrollbars, and reposition your grid
//  panel accordingly. You will also need to use a layout manager to position all three components
//  properly; I have found the GridBagLayout to be the most useful here, and there are tutorials
//  in the Oracle documentation on how to use them.

  JScrollBar horizontal;
  JScrollBar vertical;
  SpreadSheetPanel sp;

  SpreadSheetScrollPanel(SpreadSheetPanel sp, JScrollBar horizontal, JScrollBar vertical) {
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

  public void horizontalScroll(int scrollAmt) {
    this.sp.changeVerticalPosition(scrollAmt);
  }

  public void verticalScroll(int scrollAmt) {
    this.sp.changeHorizontalPosition(scrollAmt);
  }
}
