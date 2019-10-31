package cs3500.worksheet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.sexp.Parser;

/**
 * Class for testing cells.
 */
public class CellTest {
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
    assertEquals("2", c1.viewCell());
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
    assertEquals("\"hi\"", c1.viewCell());
  }
}
