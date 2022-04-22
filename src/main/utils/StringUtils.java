package main.utils;

import java.util.Arrays;

/**
 * StringUtils class. Modulizes methods that involve String
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class StringUtils {

  /**
   * Concatenate two strings in sorted order
   * @param s1
   * @param s2
   * @return concatenated two strings in sorted order.
   */
  public static String concatenateInOrder(String s1, String s2) {
    String combined = s1 + s2;
    char tempArray[] = combined.toCharArray();
    Arrays.sort(tempArray);
    return new String(tempArray);
  }


}