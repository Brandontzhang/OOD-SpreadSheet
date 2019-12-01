package edu.cs3500.spreadsheets.view.panels;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.List;

import javax.swing.JPanel;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Panel for formatting of spreadsheet view. In charge of the grid and determining where the data
 * strings are placed.
 */

public class SpreadSheetPanel extends JPanel {
  /**
   * Class for displaying parts, take in a string representing upper right corner in constructor, go
   * 10x10 from there might also need to take in data from the model to draw the cells.
   *
   * @param coord coordinates.
   */
  List<List<String>> data;

  // current position of spreadsheet
  int horizontal;
  int vertical;
  int windowWidth;
  int windowHeight;
  int selectX;
  int selectY;

  /**
   * This panel is used for displaying spreadsheet information.
   * @param data 2D array with cell data taken to be displayed on this panel.
   */
  public SpreadSheetPanel(List<List<String>> data) {
    this.data = data;
    this.horizontal = 0;
    this.vertical = 0;
    this.windowHeight = 502;
    this.windowWidth = 1002;
    this.selectX = -1;
    this.selectY = -1;
  }

  /**
   * Allows for changing of the section of the spreadsheet being displayed by adjusting the
   * vertical offset of the spreadsheet query for data.
   */
  public void changeVerticalPosition(int verticalShift) {
    this.vertical = (verticalShift * 20);
    this.selectX = -1;
    this.selectY = -1;
  }

  /**
   * Allows for changing of the section of the spreadsheet being displayed by adjusting the
   * horizontal offset of the spreadsheet query for data.
   */
  public void changeHorizontalPosition(int horizontalShift) {
    this.horizontal = (horizontalShift * 40);
    this.selectX = -1;
    this.selectY = -1;
  }

  /**
   * Allows for changing of the section of the spreadsheet being displayed by adjusting the
   * window size that can be displayed.
   */
  public void setHeightWidth(int width, int height) {
    this.windowHeight = height;
    this.windowWidth = width;
  }

  /**
   * Allows for changing of the section of the spreadsheet being displayed by adjusting the
   * color of the cell selected.
   */
  public void setSelectedCell(int x, int y) {
    this.selectX = x;
    this.selectY = y;
  }

  /**
   * Sends information upwards about which cell is being selected.
   */
  public String getCellName(int x, int y) {
    if (((y + this.vertical) / 20) <= 2) {
      return null;
    }
    return Coord.colIndexToName(((x + this.horizontal) / 80)) + (((y + this.vertical) / 20) - 2);
  }

  public void updateData(List<List<String>> data) {
    this.data = data;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;


    g2d.setColor(Color.GREEN);
    g2d.drawLine(this.getWidth(), 0, 0, this.getHeight());

    g2d.setColor(Color.BLACK);
    // Use a for loop to draw the cells and put in the content here
    int width = 80;
    int height = 20;
    for (int i = 0; i < this.windowWidth; i += width) {
      for (int j = 0; j < this.windowHeight; j += height) {
        if (i != 0 && j == 0) {
          // column names at the top
          g2d.drawString("" + Coord.colIndexToName((i + this.horizontal) / width),
                  i + 3, j + 19);
          drawData(g2d, i, j);
        } else if (i == 0 && j != 0) {
          // row names at the left most row
          g2d.drawString("" + ((j + this.vertical) / 20), i + 3, j + 19);
          drawData(g2d, i, j);
        } else {
          drawData(g2d, i, j);
        }
        if (this.selectX > i && this.selectX < i + width
                && this.selectY > j + 40 && this.selectY < j + 60) {
          g2d.setColor(Color.RED);
          g2d.draw(new Rectangle2D.Double(i + 1, j + 1, width - 2, height - 2));
        } else {
          g2d.draw(new Rectangle2D.Double(i, j, width, height));
        }
        g2d.setColor(Color.BLACK);
      }
    }
  }

  private void drawData(Graphics g2d, int i, int j) {
    try {
      String data = this.data.get(((i + this.horizontal) / 80)).get((j + this.vertical) / 20);
      // cutting string if it is too long
      if (data.length() > 8) {
        data = data.substring(0, 8);
      }
      g2d.drawString(data, i + 83, j + 39);
    } catch (IndexOutOfBoundsException e) {
      // there is no more data, nothing needs to be printed
    }
  }

}
