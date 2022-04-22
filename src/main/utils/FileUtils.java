package main.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Util class that handles external file
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class FileUtils {

  /**
   * Using Scanner returns a number of lines from a file
   * @param inputFile text file with string lines
   * @return integer number of lines
   */
  public static int getNumLines(File inputFile) throws FileNotFoundException {
    Scanner sc = new Scanner(inputFile);
    int count = 0;
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      count++;
    }
    sc.close();
    return count;
  }
}
