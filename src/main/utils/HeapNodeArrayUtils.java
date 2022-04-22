package main.utils;

import main.entities.HeapNode;


/**
 * HeapNodeArrayUtils Test class with methods for HeapNodeArray
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class HeapNodeArrayUtils {

  /**
   * Keep a node with lower integer value to the left.
   * When equal, priorities are
   * 1. Single Letter group
   * 2. Alphabetical order
   * @param heapNodeArray
   * @return HeapNode array where index 1 element is smaller than index 2
   * element
   */
  public static HeapNode[] sortHeapNodeArray(HeapNode[] heapNodeArray) {
    HeapNode h1 = heapNodeArray[0];
    HeapNode h2 = heapNodeArray[1];

    if (h1.getIntegerValue() < h2.getIntegerValue()) {
      return heapNodeArray;
    } else if (h1.getIntegerValue() > h2.getIntegerValue()) {
      HeapNode tmpNode = h1;
      heapNodeArray[0] = h2;
      heapNodeArray[1] = tmpNode;
      return heapNodeArray;
    } else {
      if (h1.getAlphabetValue().length() < h2.getAlphabetValue().length()) {
        return heapNodeArray;
      } else if (h1.getAlphabetValue().length() > h2.getAlphabetValue().length()) {
        HeapNode tmpNode = h1;
        heapNodeArray[0] = h2;
        heapNodeArray[1] = tmpNode;
        return heapNodeArray;
      } else {
        int alphabeticalOrder =
                h1.getAlphabetValue().compareToIgnoreCase(h2.getAlphabetValue());

        if (alphabeticalOrder > 0) {
          HeapNode tmpNode = h1;
          heapNodeArray[0] = h2;
          heapNodeArray[1] = tmpNode;
          return heapNodeArray;
        } else {
          return heapNodeArray;
        }
      }
    }
  }

  /**
   * Empties, or fills the array with null value
   * @param heapNodeArray
   * @return Empty HeapNode Array
   */
  public static HeapNode[] emptyHeapNodeArray(HeapNode[] heapNodeArray) {
    for (int i = 0; i < heapNodeArray.length; i++) {
      heapNodeArray[i] = null;
    }
    return heapNodeArray;
  }
}
