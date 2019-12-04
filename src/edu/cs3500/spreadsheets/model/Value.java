package edu.cs3500.spreadsheets.model;

/**
 * Value class used by provider for evaluation. Since we didn't process the same way, just created a
 * class to hold the value to use.
 */
public class Value {
  private String content;

  public Value(String s) {
    this.content = s;
  }

  @Override
  public String toString() {
    return this.content;
  }
}
