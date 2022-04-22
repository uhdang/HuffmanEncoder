package test.utils;

import main.utils.StringUtils;

/**
 * StringUtilsTest class.
 * Contains tests for methods from StringUtils class
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class StringUtilsTest {

  /**
   * Test for concatenateInOrder Method.
   * @return true if result is same as expected value
   */
  public static boolean concatenateInOrderTest() {

    String s1 = "C";
    String s2 = "B";
    String expectedOutput = "BC";
    String generatedOutput = StringUtils.concatenateInOrder(s1, s2);
    return expectedOutput.equals(generatedOutput);
  }
}
