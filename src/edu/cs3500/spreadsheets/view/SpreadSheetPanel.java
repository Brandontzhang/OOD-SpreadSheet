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
  SpreadSheetPanel(List<List<String>> data) {
    this.data = data;
    this.horizontal = 0;
    this.vertical = 0;
  }

  // allows for changing of the section of the spreadsheet being displayed
  public void changePosition(int horizontal, int vertical) {
    this.horizontal = horizontal;
    this.vertical = vertical;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

//    g2d.setColor(Color.WHITE);
//    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    g2d.setColor(Color.BLACK);
    g2d.drawLine(this.getWidth(), 0, 0, this.getHeight());

    // Use a for loop to draw the cells and put in the content here
    int width = 40;
    int height = 20;
    for(int i = 0; i < 1000; i+=width) {
      for (int j = 0; j < 500; j+=height) {
        if (i != 0 && j == 0) {
          // column names at the top
          g2d.drawString("" + Coord.colIndexToName(i / 40), i + 20 , j + 19);
        } else if ( i == 0 && j > 0) {
          // row names at the left most row
          g2d.drawString("" + (j / 20), i + 20 , j + 19);
        } else {
          // write contents from data
          try {
            g2d.drawString(this.data.get((i/40)).get(j/20), i + 20, j + 39);
          } catch(IndexOutOfBoundsException e) {

          }
        }

        g2d.draw(new Rectangle2D.Double(i, j, width, height));
      }
    }
  }
}
