package cs3500.worksheet;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.view.SpreadSheetTextualView;

/**
 * Tests for WorkSheetTextualView.
 */

public class WorkSheetTextualViewTest {
  @Test
  public void testTextualViewSub() throws IOException {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUB 3 1)");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A1 =2.0\n", view.toString());
  }

  @Test
  public void testTextualViewNum() throws IOException {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "1");
    test.updateCell("A2", "2");
    test.updateCell("A3", "3");
    test.updateCell("A4", "4");
    test.updateCell("A5", "(PRODUCT A1:A4)");

    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A1 =1.0\n" +
            "A2 =2.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =24.0\n", view.toString());
    test.updateCell("A2", "4");
    assertEquals("A1 =1.0\n" +
            "A2 =2.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =24.0\n" +
            "A1 =1.0\n" +
            "A2 =4.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =48.0\n", view.toString());
  }

  @Test
  public void testTextualSumNum() throws IOException {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "1");
    test.updateCell("A2", "2");
    test.updateCell("A3", "3");
    test.updateCell("A4", "4");
    test.updateCell("A5", "(SUM A1:A4)");

    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A1 =1.0\n" +
            "A2 =2.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =10.0\n", view.toString());
    test.updateCell("A2", "4");
    assertEquals("A1 =1.0\n" +
            "A2 =2.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =10.0\n" +
            "A1 =1.0\n" +
            "A2 =4.0\n" +
            "A3 =3.0\n" +
            "A4 =4.0\n" +
            "A5 =12.0\n", view.toString());
  }

  @Test
  public void testTextualViewString() throws IOException {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "hello");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A1 =hello\n", view.toString());
  }

  @Test
  public void testTextualView() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A100", "hello");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A100 =hello\n", view.toString());
  }

  @Test
  public void testTextualView2() {
    WorkSheet test = new WorkSheet();
    test.updateCell("AZA1", "true");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("AZA1 =true\n", view.toString());
  }

  @Test
  public void testTextualView3() {
    WorkSheet test = new WorkSheet();
    test.updateCell("AZ500", "(< 11 10)");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("AZ500 =false\n", view.toString());
  }

  @Test
  public void testTextualView4() {
    WorkSheet test = new WorkSheet();
    test.updateCell("AD51", "(< 5 10)");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("AD51 =true\n", view.toString());
  }

  @Test
  public void testTextualView5() {
    WorkSheet test = new WorkSheet();
    test.updateCell("B7", "5");
    test.updateCell("C10", "8");
    test.updateCell("AD51", "(< B7 C10)");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("B7 =5.0\n" +
            "C10 =8.0\n" + "AD51 =true\n", view.toString());
  }

  @Test
  public void testTextualView6() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A10", "\"hello\"");
    test.updateCell("AZ500", "(< 11 10)");
    test.updateCell("CD22", "(APPEND A10 AZ500)");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A10 =\"hello\"\n" +
            "AZ500 =false\n" +
            "CD22 =\"hellofalse\"\n", view.toString());
  }

  @Test
  public void testTextualView7() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A11", "\"hello this is a long string\"");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A11 =\"hello this is a long string\"\n", view.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTextualView8() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A1", "(SUM B2 C3)");
    test.updateCell("B2", "(SUM A1 A2)");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
  }

  @Test
  public void testTextualView9() {
    WorkSheet test = new WorkSheet();
    test.updateCell("A11", "1");
    test.updateCell("B1", "A11");
    SpreadSheetTextualView view = new SpreadSheetTextualView(test);
    assertEquals("A11 =1.0\n" +
            "B1 =1.0\n", view.toString());
  }
}
