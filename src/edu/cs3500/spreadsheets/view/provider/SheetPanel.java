package edu.cs3500.spreadsheets.view.provider;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.IWorksheetProvider;
import edu.cs3500.spreadsheets.model.Value;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Represents the component that contains all of the information for the spreadsheet table body
 * that a GUI view for spreadsheets will contain.
 */
public class SheetPanel extends JPanel {

  IWorksheetProvider model;
  private JTable table;
  private JScrollPane scrollPane;
  private int viewableRows;
  private int viewableColumns;

  /**
   * Constructs a SheetPanel component using the given model to evaluate each cell.
   * @param model the model to be viewed
   */
  public SheetPanel(IWorksheetProvider model) {
    super();
    this.model = model;
    this.viewableRows = 100;
    this.viewableColumns = 100;
    TableModel dataModel = new DefaultTableModel() {
      public int getColumnCount() {
        return Math.max(100, viewableColumns);
      }

      public int getRowCount() {
        return Math.max(100, viewableRows);
      }

      public String getValueAt(int row, int col) {
        //return model.evaluateCoord(new Coord(col + 1, row + 1));
        return "hello";
      }

      public Class<?> getColumnClass(int i) {
        return Cell.class;
      }
    };
    this.table = new JTable(dataModel);

    DefaultTableModel headerModel = new DefaultTableModel() {
      public int getColumnCount() {
        return 1;
      }

      public int getRowCount() {
        return dataModel.getRowCount();
      }

      public String getValueAt(int row, int col) {
        return "Row " + (row + 1);
      }
    };
    this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    this.table.setCellSelectionEnabled(true);

    this.table.setDefaultRenderer(Cell.class, (jTable, o, b, b1, i12, i1) -> {
      Component c = this.table.getDefaultRenderer(String.class)
          .getTableCellRendererComponent(jTable, o, false, b1, i12, i1);
      ((JComponent) c).setBorder(BorderFactory.createLineBorder(Color.BLACK));
      return c;
    });

    // Row Header code
    JTable headerTable = new JTable(headerModel);
    for (int i = 0; i < this.table.getRowCount(); i++) {
      headerTable.setValueAt("Row " + (i + 1), i, 0);
    }
    headerTable.setShowGrid(false);
    headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    headerTable.setPreferredScrollableViewportSize(new Dimension(50, 0));
    headerTable.getColumnModel().getColumn(0).setPreferredWidth(50);
    headerTable.getColumnModel().getColumn(0).setCellRenderer(
        (x, value, isSelected, hasFocus, row, column) -> {
          Component component = this.table.getTableHeader().getDefaultRenderer()
              .getTableCellRendererComponent(this.table, value, false, false, -1, -2);
          ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
          component.setFont(component.getFont().deriveFont(Font.PLAIN));
          return component;
        });

    System.out.println(this.model.getCellAt(new Coord(1,1)).getUnevalContent());
    System.out.println(this.table.getValueAt(0, 0));
    this.scrollPane = new JScrollPane(this.table);
    this.scrollPane.setRowHeaderView(headerTable);
  }

  /**
   * Allows the controller to access the scrollpane of the view.
   * @return the scrollpane of this panel
   */
  public JScrollPane getScrollPane() {
    return this.scrollPane;
  }

  /**
   * Updates the cells of this panel to reflect a modification in one or more cells.
   */
  public void updateCells() {
    ((AbstractTableModel) this.table.getModel()).fireTableDataChanged();
  }

  /**
   * Gets the currently selected row of this panel.
   * @return the currently selected row
   */
  public int getRow() {
    return this.table.getSelectedRow();
  }

  /**
   * Gets the currently selected column of this panel.
   * @return the currently selected column
   */
  public int getCol() {
    return this.table.getSelectedColumn();
  }

  /**
   * Doubles the amount of columns in this panel if given a viewed column amount of more than
   * half the currently shown number.
   * @param colNum the number of columns currently being focused on
   */
  public void doubleColumns(int colNum) {
    if (colNum > this.viewableColumns / 2) {
      this.viewableColumns *= 2;
      ((DefaultTableModel) this.table.getModel()).fireTableStructureChanged();
    }
  }

  /**
   * Doubles the amount of rows in this panel if given a viewed row amount of more than
   * half the currently shown number.
   * @param rowNum the number of rows currently being focused on
   */
  public void doubleRows(int rowNum) {
    if (rowNum > this.viewableRows / 2) {
      this.viewableRows *= 2;
    }
  }

  /**
   * Connects this panel to a JTextField object which will then be updated with the contents
   * of the selected cell whenever a new cell is selected.
   * @param listener the textfield that is updated whenever a new cell is selected
   */
  public void setSelectionListener(JTextField listener) {
    this.table.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
      if (table.getSelectedColumn() >= 0 && table.getSelectedRow() >= 0) {
        Cell c = model.getCellAt(new Coord(table.getSelectedColumn() + 1,
            table.getSelectedRow() + 1));
        if (c != null) {
          listener.setText(c.getUnevalContent());
        }
      }
    });
  }
}
