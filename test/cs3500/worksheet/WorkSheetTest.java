package cs3500.worksheet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * Class for testing the worksheet.
 */
public class WorkSheetTest {
  @Test
  public void creationTest() {
    WorkSheet test = new WorkSheet();
    assertEquals("", test.getCell("A1"));
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
  public void testValidAddress5() {
    boolean t = WorkSheet.validCellAddress("AAAA");
    assertFalse(t);
  }

  @Test
  public void testValidAddress6() {
    boolean t = WorkSheet.validCellAddress("<");
    assertFalse(t);
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
    assertEquals("", test.getCell("M15"));
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
    assertEquals("", test.getCell("Z50"));
    test.updateCell("Z50", "(SUB 2 1)");
    assertEquals("1.0", test.getCell("Z50"));
    assertEquals("", test.getCell("Z49"));
  }

  @Test
  public void accessCellTest4() {
    // checking what happens when a call is made out of bounds
    WorkSheet test = new WorkSheet();
    assertEquals("", test.getCell("AA50"));
    test.updateCell("AA50", "(SUM 2 1)");
    assertEquals("3.0", test.getCell("AA50"));
    assertEquals("", test.getCell("AA49"));
  }

  //Test inter Cells interactions-------------------------------------------------------------------
  @Test
  public void interCellTest() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUB 3 1)");
    test.updateCell("A2", "(SUM 5 1)");
    test.updateCell("A3", "(PRODUCT A1 A2 1)");
    assertEquals("12.0", test.getCell("A3"));
  }

  @Test
  public void interCellTest2() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUB 3 1)");
    test.updateCell("A2", "(SUM 5 1)");
    test.updateCell("A3", "2");
    test.updateCell("A4", "(PRODUCT A1:A3)");
    assertEquals("24.0", test.getCell("A4"));
  }

  @Test
  public void interCellTest3() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "A2");
    test.updateCell("A2", "3");
    assertEquals("3.0", test.getCell("A1"));
  }

  @Test
  public void interCellTest4() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUB 3 1)");
    test.updateCell("A2", "(SUM 5 1)");
    test.updateCell("A3", "(PRODUCT A1 A2 1)");
    test.updateCell("A4", "(SUM A1 A2 A3)");
    assertEquals("2.0", test.getCell("A1"));
    assertEquals("6.0", test.getCell("A2"));
    assertEquals("12.0", test.getCell("A3"));
    assertEquals("20.0", test.getCell("A4"));
  }

  @Test
  public void interCellTest5() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUB 3 1)");
    test.updateCell("A2", "(SUM 5 1)");
    test.updateCell("A3", "(PRODUCT A1 A2 1)");
    test.updateCell("A4", "(SUM A1 A2 A3)");
    test.updateCell("A5", "(SUM A1:A4)");
    assertEquals("2.0", test.getCell("A1"));
    assertEquals("6.0", test.getCell("A2"));
    assertEquals("12.0", test.getCell("A3"));
    assertEquals("20.0", test.getCell("A4"));
    assertEquals("40.0", test.getCell("A5"));
  }

  // Evaluating SUB operation cell tests -----------------------------------------------------------

  @Test
  public void evaluateSubCellTest() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB 2 1)");
    assertEquals("1.0", test.getCell("C1"));
  }

  @Test
  public void evaluateSubCellTest2() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB 2.5 1.2)");
    assertEquals("1.3", test.getCell("C1"));
  }

  @Test
  public void evaluateSubCellTest3() {
    // + sub -
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB 2.5 1.2) (SUB 5.0 7.0))");
    assertEquals("3.3", test.getCell("C1"));
  }

  @Test
  public void evaluateSubCellTest4() {
    // + sub +
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB 10 (SUB 8.0 7.0))");
    assertEquals("9.0", test.getCell("C1"));

  }

  @Test
  public void evaluateSubCellTest5() {
    // - sub +
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB 10 11) (SUB 8.0 7.0))");
    assertEquals("-2.0", test.getCell("C1"));
  }

  @Test
  public void evaluateSubCellTest6() {
    // - sub -
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB 10 11) (SUB 6.0 7.0))");
    assertEquals("0.0", test.getCell("C1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSubCellTest7() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB 10 a) (SUB 6.0 7.0))");
    //test.getCell("C1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSubCellTest8() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB a 9) (SUB 6.0 7.0))");
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSubCellTest9() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB 10 11) (SUB a 7.0))");
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSubCellTest10() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB 10 11) (SUB 6.0 a))");
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSubCellTest11() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB 7 8 9)");
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSubCellTest12() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB 10 9 11) (SUB 6.0 7.0))");
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSubCellTest13() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB (SUB 10 11) (SUB 6.0 7.0 1.0))");
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSubCellTest14() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUB 7.0)");
  }

  // Evaluating < Cell Tests -----------------------------------------------------------------------
  @Test
  public void evaluateLessThanCellTest() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(< 5 7)");
    assertEquals("true", test.getCell("C1"));
  }

  @Test
  public void evaluateLessThanCellTest2() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(< 7 5)");
    assertEquals("false", test.getCell("C1"));
  }

  @Test
  public void evaluateLessThanCellTest3() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(< 7 7)");
    assertEquals("false", test.getCell("C1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateLessThanCellTest4() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(< 5 7 8)");
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateLessThanCellTest5() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(< 5)");
  }

  // Evaluating Product Cell Tests -----------------------------------------------------------------
  @Test
  public void evaluateProductCellTest() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(PRODUCT 2 3)");
    assertEquals("6.0", test.getCell("C1"));
  }

  @Test
  public void evaluateProductCellTest2() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(PRODUCT 2 3 2 3)");
    assertEquals("36.0", test.getCell("C1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateProductCellTest3() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(PRODUCT 7)");
  }

  // Evaluating Sum Cell Tests ---------------------------------------------------------------------
  @Test
  public void evaluateSumCellTest() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUM 2 3)");
    assertEquals("5.0", test.getCell("C1"));
  }

  @Test
  public void evaluateSumCellTest2() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUM 2 3 2 3)");
    assertEquals("10.0", test.getCell("C1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void evaluateSumCellTest3() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(SUM 2)");
  }

  // Evaluating Append Cell Tests ------------------------------------------------------------------
  @Test
  public void evaluateAppendCellTest() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "\"Hello \"");
    test.updateCell("B1", "\"World\"");
    test.updateCell("C1", "(APPEND A1 B1)");
    assertEquals("\"Hello World\"", test.getCell("C1"));
  }

  @Test
  public void evaluateAppendCellTest2() {
    WorkSheet test = new WorkSheet();

    test.updateCell("C1", "(APPEND \"Answer: \" (SUM 1 2))");
    assertEquals("\"Answer: 3.0\"", test.getCell("C1"));
  }

  // Combination evaluation Cell Tests -------------------------------------------------------------
  @Test
  public void evaluateCombinationCellTest() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(< (SUB 7.0 1.0) 10)");
    assertEquals("true", test.getCell("C1"));
  }

  @Test
  public void evaluateCombinationCellTest2() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "(< 10 (SUB 7.0 1.0))");
    assertEquals("false", test.getCell("C1"));
  }

  // Cyclic tests ----------------------------------------------------------------------------------
  @Test
  public void cyclicCellTest() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUB (SUB 3 1) (SUB 4 1))");
    assertEquals("-1.0", test.getCell("A1"));
    test.updateCell("A2", "A1");
    assertEquals("-1.0", test.getCell("A2"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void cyclicCellTest2() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "B1");
    test.updateCell("B1", "A1");
  }

  @Test (expected = IllegalArgumentException.class)
  public void cyclicCellTest3() {
    WorkSheet test = new WorkSheet();
    test.updateCell("C1", "1");
    test.updateCell("A1", "(SUB B1 C1)");
    test.updateCell("B1", "A1");
  }
}
