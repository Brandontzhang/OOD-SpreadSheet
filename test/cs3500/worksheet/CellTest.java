package cs3500.worksheet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;

public class CellTest {

  @Test
  public void createCellTest() {
    Cell c1 = new Cell();
    System.out.println(c1.evaluateCell());
  }
}
