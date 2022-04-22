package test.entities;

import main.entities.HeapNode;
import main.entities.HeapStack;
import main.entities.MinHeap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * Unit test for MinHeap Class
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-18
 */
public class MinHeapTest {

  /**
   * Test method for the method `generateMinHeapArray`.
   * @param fileName
   * @param expectedAlphabet
   * @return
   * @throws FileNotFoundException
   */
  public static boolean generateMinHeapArrayTest(String fileName,
                                                 String[] expectedAlphabet)
          throws FileNotFoundException {
    boolean pass = true;

    MinHeap minHeapInstance = new MinHeap(new File("src/test/inputs/" + fileName));
    HeapNode[] minHeap = minHeapInstance.generateMinHeapArray();

    for (int i = 0; i < minHeap.length; i++) {
      if (!Objects.equals(minHeap[i].getAlphabetValue(), expectedAlphabet[i])) {
        pass = false;
      }
    }
    return pass;
  }

  public static boolean removeTopNodeTest(String fileName,
                                          String expectedLetter) throws FileNotFoundException {
    MinHeap minHeapInstance =
            new MinHeap(new File("src/test/inputs/" + fileName));
    minHeapInstance.generateMinHeapArray();
    HeapNode removedNode = minHeapInstance.removeTopNode();
    return removedNode.getAlphabetValue().equals(expectedLetter);
  }
}
