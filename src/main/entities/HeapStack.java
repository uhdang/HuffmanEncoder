package main.entities;

/**
 * HeapStack class. This is used to collect pieces of height 1 trees while
 * constructing Hoffmann encoding tree
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class HeapStack {
  HeapNode[][] heapStackArray;
  int topIndex;

  public HeapNode[][] getHeapStackArray() {
    return heapStackArray;
  }

  public int getTopIndex() {
    return topIndex;
  }

  /**
   * HeapStack constructor. Initializes the stack out of the array.
   * @param size
   */
  public HeapStack(int size) {
    this.heapStackArray = new HeapNode[size][];
    this.topIndex = -1;
  }

  /**
   * Pushes an element to top of the stack updating topIndex access point.
   * @param heapNodeArray
   */
  public void push(HeapNode[] heapNodeArray) {
    this.topIndex++;
    this.heapStackArray[this.topIndex] = heapNodeArray;
  }

  /**
   * Pops the top element from the stack and returns it while deducting
   * topIndex access point.
   * @return top HeapNode[]
   */
  public HeapNode[] pop() {
    HeapNode[] topHeapNodeArray = this.heapStackArray[this.topIndex];
    this.topIndex--;
    return topHeapNodeArray;
  }

  /**
   * See if the stack is empty or not
   * @return true if empty otherwise false
   */
  public boolean isEmpty() {
    return this.topIndex == -1;
  }

  /**
   * Return the top element of the stack while it does not remove it.
   * @return Top element of the stack
   */
  public HeapNode[] peek() {
    HeapNode[] topHeapNodeArray = this.heapStackArray[this.topIndex];
    return topHeapNodeArray;
  }

}
