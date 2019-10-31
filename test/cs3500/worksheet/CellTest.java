package cs3500.worksheet;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.sexp.Parser;

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
  public void parserTest() {
    Parser cellParser = new Parser();
    assertEquals("2.0", cellParser.parse("2").toString());
  }


  // Creating Cell Tests ---------------------------------------------------------------------------
  @Test
  public void createCellTest() {
    Cell c1 = new Cell();
    assertEquals("", c1.viewCell());
  }

  @Test
  public void createCellTest2() {
    Cell c1 = new Cell(2);
    assertEquals("2.0", c1.viewCell());
  }

  @Test
  public void createCellTest3() {
    Cell c1 = new Cell(true);
    Cell c2 = new Cell(false);
    assertEquals("true", c1.viewCell());
    assertEquals("false", c2.viewCell());
  }

  @Test
  public void createCellTest4() {
    // symbol
    Cell c1 = new Cell("Hello");
    assertEquals("Hello", c1.viewCell());
  }

  @Test
  public void createCellTest5() {
    // string
    Cell c1 = new Cell("\"hi\"");
    assertEquals("hi", c1.viewCell());
  }
}
