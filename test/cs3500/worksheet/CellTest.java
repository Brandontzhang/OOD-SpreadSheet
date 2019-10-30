package cs3500.worksheet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;
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
    Cell c1 = new Cell("Hello");
    assertEquals("Hello", c1.viewCell());
  }

  @Test
  public void evaluateCellTest() {
    Cell c1 = new Cell("(SUB 3 1)");
    assertEquals("2.0", c1.viewCell());
  }

  @Test
  public void evaluateCellTest2() {
    Cell c1 = new Cell("(SUB 2.5 1.2)");
    assertEquals("1.3", c1.viewCell());
  }

  @Test
  public void evaluateCellTest3() {
    // + sub -
    Cell c1 = new Cell("(SUB (SUB 2.5 1.2) (SUB 5.0 7.0))");
    assertEquals("3.3", c1.viewCell());
  }

  @Test
  public void evaluateCellTest4() {
    // + sub +
    Cell c1 = new Cell("(SUB 10 (SUB 8.0 7.0))");
    assertEquals("9.0", c1.viewCell());
  }

  @Test
  public void evaluateCellTest5() {
    // - sub +
    Cell c1 = new Cell("(SUB (SUB 10 11) (SUB 8.0 7.0))");
    assertEquals("-2.0", c1.viewCell());
  }

  @Test
  public void evaluateCellTest6() {
    // - sub -
    Cell c1 = new Cell("(SUB (SUB 10 11) (SUB 6.0 7.0))");
    assertEquals("0.0", c1.viewCell());
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTest7() {
    Cell c1 = new Cell("(SUB (SUB 10 a) (SUB 6.0 7.0))");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTest8() {
    Cell c1 = new Cell("(SUB (SUB a 9) (SUB 6.0 7.0))");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTest9() {
    Cell c1 = new Cell("(SUB (SUB 10 9) (SUB a 7.0))");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTest10() {
    Cell c1 = new Cell("(SUB (SUB 10 9) (SUB 6.0 a))");
  }
}
