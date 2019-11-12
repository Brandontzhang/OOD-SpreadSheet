package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;

public class SpreadSheetPanel extends javax.swing.JPanel{
  /**
   * Class for displaying parts, take in a string representing upper right corner in constructor, go 10x10 from there
   * might also need to take in data from the model to draw the cells
   * @param coord
   */
  List<List<String>> data;

  // current position of spreadsheet
  int horizontal;
  int vertical;
  int windowWidth;
  int windowHeight;

  SpreadSheetPanel(List<List<String>> data) {
    this.data = data;
    this.horizontal = 0;
    this.vertical = 0;
    this.windowHeight = 502;
    this.windowWidth = 1002;
  }

  // allows for changing of the section of the spreadsheet being displayed
  public void changeVerticalPosition(int verticalShift) {
    this.vertical = (verticalShift * 20);
  }

  // allows for changing of the section of the spreadsheet being displayed
  public void changeHorizontalPosition(int horizontalShift) {
    this.horizontal = (horizontalShift * 40);
  }

  // keep track of size of window
  public void setHeightWidth(int width, int height) {
    this.windowHeight = height;
    this.windowWidth = width;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

//    g2d.setColor(Color.WHITE);
//    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    g2d.setColor(Color.GREEN);
    g2d.drawLine(this.getWidth(), 0, 0, this.getHeight());

    g2d.setColor(Color.BLACK);
    // Use a for loop to draw the cells and put in the content here
    int width = 40;
    int height = 20;
    for(int i = 0; i < this.windowWidth; i+=width) {
      for (int j = 0; j < this.windowHeight; j+=height) {
        if (i != 0 && j == 0) {
          // column names at the top
          g2d.drawString("" + Coord.colIndexToName((i + this.horizontal) / 40), i , j + 19);
          drawData(g2d, i, j);
        } else if ( i == 0 && j > 0) {
          // row names at the left most row
          g2d.drawString("" + ((j + this.vertical) / 20), i , j + 19);
          drawData(g2d, i, j);
        } else {
          drawData(g2d, i, j);
        }

        g2d.draw(new Rectangle2D.Double(i, j, width, height));
      }
    }
  }

  private void drawData(Graphics g2d, int i, int j) {
    try {
      g2d.drawString(this.data.get(((i + this.horizontal) / 40)).get((j + this.vertical) / 20), i, j + 39);
    } catch(IndexOutOfBoundsException e) {
      // there is no more data, nothing needs to be printed
    }
  }
}
