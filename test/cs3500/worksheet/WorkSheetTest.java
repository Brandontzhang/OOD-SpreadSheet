package cs3500.worksheet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.WorkSheet;

public class WorkSheetTest {
  /** WorkSheet Tests
   * Creation test
   * - Not exactly sure how this will go yet
   *
   * Cell tests
   * - user can access and see what cells evaluate to  (test getCell)
   * - user can update cells                           (test updateCell)
   * - user can get a list of cells                    (test getRegionCells)
   *
   */

  @Test
  public void creationTest() {
    WorkSheet test = new WorkSheet();
  }

  @Test
  public void testValidAddress() {
    boolean t = WorkSheet.validCellAddress("A1");
    assertTrue(t);
  }

  @Test
  public void testValidAddress2() {
    boolean t = WorkSheet.validCellAddress("1A1");
    assertFalse(t);
  }

  @Test
  public void testValidAddress3() {
    boolean t = WorkSheet.validCellAddress("A1A");
    assertFalse(t);
  }

  @Test
  public void testValidAddress4() {
    boolean t = WorkSheet.validCellAddress("AAAA1111");
    assertTrue(t);
  }

  @Test
  public void updateCellTest() {
    WorkSheet test = new WorkSheet();
    assertEquals("", test.getCell("B1"));
    test.updateCell("B1", "(SUB 2 1)");
    assertEquals("1.0", test.getCell("B1"));
    test.updateCell("B2", "2");
    assertEquals("2.0", test.getCell("B2"));
    assertEquals("1.0", test.getCell("B1"));
    assertEquals("", test.getCell("A1"));
  }

  @Test
  public void accessCellTest() {
    WorkSheet test = new WorkSheet();
    assertEquals("", test.getCell("A1"));
  }

  @Test
  public void accessCellTest2() {
    WorkSheet test = new WorkSheet();
    assertEquals("", test.getCell("D3"));
  }

  @Test
  public void accessCellTest3() {
    // checking what happens when a call is made out of bounds
    WorkSheet test = new WorkSheet();
    assertEquals("", test.getCell("A10"));
    //test.updateCell("A10", "(SUB 2 1)");
  }
}
