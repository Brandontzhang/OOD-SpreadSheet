package cs3500.worksheet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
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
    assertEquals(null, c1.getContent());
  }

  @Test
  public void createCellTest2() {
    Cell c1 = new Cell("2");
    assertEquals("2.0", c1.getContent().toString());
  }

  @Test
  public void createCellTest3() {
    Cell c1 = new Cell("true");
    Cell c2 = new Cell("false");
    assertEquals("true", c1.getContent().toString());
    assertEquals("false", c2.getContent().toString());
  }

  @Test
  public void createCellTest4() {
    // symbol
    Cell c1 = new Cell("Hello");
    assertEquals("Hello", c1.getContent().toString());
  }

  @Test
  public void createCellTest5() {
    // string
    Cell c1 = new Cell("\"hi\"");
    assertEquals("\"hi\"", c1.getContent().toString());
  }

  @Test
  public void updateCellTest2() {
    Cell c1 = new Cell();
    c1.updateCell("2");
    assertEquals("2.0", c1.getContent().toString());
  }

  @Test
  public void updateCellTest3() {
    Cell c1 = new Cell();
    Cell c2 = new Cell();
    c1.updateCell("true");
    c2.updateCell("false");
    assertEquals("true", c1.getContent().toString());
    assertEquals("false", c2.getContent().toString());
  }

  @Test
  public void updateCellTest4() {
    // symbol
    Cell c1 = new Cell();
    c1.updateCell("Hello");
    assertEquals("Hello", c1.getContent().toString());
  }

  @Test
  public void updateCellTest5() {
    // string
    Cell c1 = new Cell();
    c1.updateCell("\"hi\"");
    assertEquals("\"hi\"", c1.getContent().toString());
  }

  @Test
  public void coordTest() {
    System.out.println(Coord.colIndexToName(11));
  }
}
