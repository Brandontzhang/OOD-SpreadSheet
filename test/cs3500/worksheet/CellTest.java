package cs3500.worksheet;

import org.junit.Assert;
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
    Cell c1 = new Cell("Hello");
    assertEquals("Hello", c1.viewCell());
  }

  // Evaluating SUB operation cell tests -----------------------------------------------------------
  @Test
  public void evaluateSubCellTest() {
    Cell c1 = new Cell("(SUB 3 1)");
    assertEquals("2.0", c1.viewCell());
  }

  @Test
  public void evaluateSubCellTest2() {
    Cell c1 = new Cell("(SUB 2.5 1.2)");
    assertEquals("1.3", c1.viewCell());
  }

  @Test
  public void evaluateSubCellTest3() {
    // + sub -
    Cell c1 = new Cell("(SUB (SUB 2.5 1.2) (SUB 5.0 7.0))");
    assertEquals("3.3", c1.viewCell());
  }

  @Test
  public void evaluateSubCellTest4() {
    // + sub +
    Cell c1 = new Cell("(SUB 10 (SUB 8.0 7.0))");
    assertEquals("9.0", c1.viewCell());
  }

  @Test
  public void evaluateSubCellTest5() {
    // - sub +
    Cell c1 = new Cell("(SUB (SUB 10 11) (SUB 8.0 7.0))");
    assertEquals("-2.0", c1.viewCell());
  }

  @Test
  public void evaluateSubCellTest6() {
    // - sub -
    Cell c1 = new Cell("(SUB (SUB 10 11) (SUB 6.0 7.0))");
    assertEquals("0.0", c1.viewCell());
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateSubCellTest7() {
    Cell c1 = new Cell("(SUB (SUB 10 a) (SUB 6.0 7.0))");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateSubCellTest8() {
    Cell c1 = new Cell("(SUB (SUB a 9) (SUB 6.0 7.0))");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateSubCellTest9() {
    Cell c1 = new Cell("(SUB (SUB 10 9) (SUB a 7.0))");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateSubCellTest10() {
    Cell c1 = new Cell("(SUB (SUB 10 9) (SUB 6.0 a))");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateSubCellTest11() {
    Cell c1 = new Cell("(SUB 7 8 9)");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateSubCellTest12() {
    Cell c1 = new Cell("(SUB (SUB 10 9 1) (SUB 6.0 7.0))");
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateSubCellTest13() {
    Cell c1 = new Cell("(SUB (SUB 10 9) (SUB 6.0 7.0 1.0))");
  }

  // Evaluating < Cell Tests -----------------------------------------------------------------------
  @Test
  public void evaluateLessThanCellTest() {
    Cell c1 = new Cell("(< 5 7)");
    assertEquals("true", c1.viewCell());
  }

  @Test
  public void evaluateLessThanCellTest2() {
    Cell c1 = new Cell("(< 7 5)");
    assertEquals("false", c1.viewCell());
  }

  @Test
  public void evaluateLessThanCellTest3() {
    Cell c1 = new Cell("(< 7 7)");
    assertEquals("false", c1.viewCell());
  }

  // Combination evaluation Cell Tests -------------------------------------------------------------
  @Test
  public void evaluateCombinationCellTest() {
    Cell c1 = new Cell("(< (SUB 7.0 1.0) 10)");
    assertEquals("true", c1.viewCell());
  }

  @Test
  public void evaluateCombinationCellTest2() {
    Cell c1 = new Cell("(< 10 (SUB 7.0 1.0))");
    assertEquals("false", c1.viewCell());
  }
}
