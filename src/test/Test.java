package test;

import test.entities.HuffmanEncodingTreeTest;
import test.entities.MinHeapTest;
import test.utils.HeapNodeArrayUtilsTest;
import test.utils.StringUtilsTest;
import java.io.FileNotFoundException;

/**
 * Test class that activates all available tests
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class Test {

  /**
   * One Test to activate all.
   * Having all tests in boolean array that expects `true` as elements,
   * later it gets checked if there is any `false` in the array thereby
   * checking if all tests pass.
   */
  public static void main(String[] args) throws FileNotFoundException {
    HuffmanEncodingTreeTest heTreeTest = new HuffmanEncodingTreeTest("src/test/inputs/FreqTable_unix.txt");

    boolean[] MinHeapTests = {
            MinHeapTest.generateMinHeapArrayTest("FreqTable_test_1.txt",
                    new String[]{"A", "B", "C", "D", "E"}),
            MinHeapTest.generateMinHeapArrayTest("FreqTable_test_2.txt",
                    new String[]{"Q", "X", "Z", "O", "V", "P", "U", "R", "W", "S", "Y", "T"}),
            MinHeapTest.generateMinHeapArrayTest("FreqTable_test_3.txt",
                    new String[]{"J", "K", "F", "I", "D", "C", "G", "A", "H", "E", "B", "L", "M", "N"}),
            MinHeapTest.generateMinHeapArrayTest("FreqTable_test_4.txt",
                    new String[]{"H", "G", "B", "A", "E", "F", "C", "D"}),
            MinHeapTest.generateMinHeapArrayTest("FreqTable_test_5.txt",
                    new String[]{"B", "A", "C", "D", "E"}),
            MinHeapTest.generateMinHeapArrayTest("FreqTable_unix.txt",
                    new String[]{"Q","J","X","K","V","Z","G","P","H","U","D","F","Y","N","O","A","I","R","S","E","T","B","W","L","C","M"}),
            MinHeapTest.removeTopNodeTest("FreqTable_test_4.txt", "H"),
            MinHeapTest.removeTopNodeTest("FreqTable_test_5.txt", "B"),

    };

    boolean[] HeapNodeArrayUtilsTests = {
            HeapNodeArrayUtilsTest.sortHeapNodeArrayTest01(),
            HeapNodeArrayUtilsTest.sortHeapNodeArrayTest02(),
            HeapNodeArrayUtilsTest.emptyHeapNodeArrayTest(),
    };

    boolean[] SingleUtilsTests = {
            StringUtilsTest.concatenateInOrderTest(),
    };

    boolean[] HuffmanEncodingTreeTests = {
            heTreeTest.encodeSingleLetterTest("A", "11111"),
            heTreeTest.encodeSingleLetterTest("Q", "10110010"),
            heTreeTest.encodeTest("Hello World",
                    "110110100001000111110 0011111101000000101100"),
            heTreeTest.decodeSingleLetterTest("11010", "C"),
            heTreeTest.decodeTest("110110100001000111110", "Hello"),
            heTreeTest.decodeTest("0011111101000000101100", "World"),
            heTreeTest.decodeTest("110110100001000111110 0011111101000000101100", "Hello World"),
            heTreeTest.decodeTest("01011001010110011111011011", "EIEIOH"),
    };

    Test.executeTests("MinHeapTests", MinHeapTests);
    Test.executeTests("HeapNodeArrayUtilsTests", HeapNodeArrayUtilsTests);
    Test.executeTests("SingleUtilsTests",SingleUtilsTests);
    Test.executeTests("HuffmanEncodingTreeTests", HuffmanEncodingTreeTests);

  }

  public static void executeTests(String testName, boolean[] testArray) {
    boolean allPass = true;
    int failingTestIndex = 0;
    for (int i = 0; i < testArray.length; i++) {
      if (!testArray[i]) {
        allPass = false;
        failingTestIndex = i;
      }
    }

    if (allPass) {
      System.out.println("[" + testName +  "] All tests pass");
    } else {
      System.out.println("[" + testName + "] There is a failing test. " +
              "FailingTestIndex is " + failingTestIndex);
    }
  }
}
