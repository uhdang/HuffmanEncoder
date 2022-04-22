package test.utils;

import main.entities.HeapNode;
import main.utils.HeapNodeArrayUtils;

/**
 * HeadNodeArrayUtils Test class containing tests for methods from
 * HeapNodeArrayUtils class
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class HeapNodeArrayUtilsTest {

  /**
   * Sorting method with tie-breaking logic
   * @return sorted HeapNode Array
   */
  public static boolean sortHeapNodeArrayTest01() {
    HeapNode h1 = new HeapNode("AB", 5);
    HeapNode h2 = new HeapNode("C", 5);

    HeapNode[] heapNodeArray = {h1, h2};
    HeapNode[] sortedHeapNodeArray =
            HeapNodeArrayUtils.sortHeapNodeArray(heapNodeArray);
    return sortedHeapNodeArray[0] == h2 & sortedHeapNodeArray[1] == h1;
  }

  /**
   * Sorting method with tie-breaking logic
   * @return sorted HeapNode Array
   */
  public static boolean sortHeapNodeArrayTest02() {
    HeapNode h1 = new HeapNode("AB", 5);
    HeapNode h2 = new HeapNode("DF", 5);

    HeapNode[] heapNodeArray = {h1, h2};
    HeapNode[] sortedHeapNodeArray =
            HeapNodeArrayUtils.sortHeapNodeArray(heapNodeArray);
    return sortedHeapNodeArray[0] == h1 & sortedHeapNodeArray[1] == h2;
  }


  /**
   * Test for emptyHeapNodeArray method from HeapNodeArrayUtils class
   * @return boolean if test returns expected output
   */
  public static boolean emptyHeapNodeArrayTest() {
    HeapNode parentNode = new HeapNode("B", 30);
    HeapNode leftChildNode = new HeapNode("X", 12);
    HeapNode rightChildNode = new HeapNode("J", 18);
    HeapNode[] input = new HeapNode[]{parentNode, leftChildNode,
            rightChildNode};

    HeapNode[] output = HeapNodeArrayUtils.emptyHeapNodeArray(input);

    return output[0] == null & output[1] == null & output[2] == null;
  }
}
