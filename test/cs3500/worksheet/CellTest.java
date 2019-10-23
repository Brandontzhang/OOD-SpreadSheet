package cs3500.worksheet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;

public class CellTest {
  /** Tests to consider
   * Creation tests
   * - creating a cell with different Sexp
   *    - Empty Cell
   *    - SBoolean
   *    - SNumber
   *    - SString
   *    - SList
   * Evaluation tests
   * - Check that cells holding different Sexp all evaluate correctly when calling evaluateCell
   */

  @Test
  public void createCellTest() {
    Cell c1 = new Cell();
    System.out.println(c1.evaluateCell());
  }
}
