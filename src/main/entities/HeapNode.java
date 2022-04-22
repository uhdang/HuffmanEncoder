package main.entities;

import main.utils.StringUtils;

/**
 * HeapNode that consist of elements for tree node.
 * This will make of MinHeap
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class HeapNode {
  private String alphabetValue;
  private int integerValue;
  private HeapNode leftChild;
  private HeapNode rightChild;

  public HeapNode getLeftChild() {
    return leftChild;
  }

  public HeapNode getRightChild() {
    return rightChild;
  }

  public void setLeftChild(HeapNode leftChild) {
    this.leftChild = leftChild;
  }

  public void setRightChild(HeapNode rightChild) {
    this.rightChild = rightChild;
  }

  /**
   * Constructor for HeapNode
   * @param alphabetValue
   * @param integerValue
   */
  public HeapNode(String alphabetValue, int integerValue) {
    this.alphabetValue = alphabetValue;
    this.integerValue = integerValue;
  }

  /**
   * Identifies if the node is a leaf node, no left nor right child
   * @return
   */
  public boolean isLeafNode() {
    return this.leftChild == null & this.rightChild == null;
  }

  /**
   * Getter for Alphabet Value from a Node
   * @return alphabet value
   */
  public String getAlphabetValue() {
    return this.alphabetValue;
  }

  /**
   * Getter for Integer Value from a Node
   * @return integer value
   */
  public int getIntegerValue() {
    return this.integerValue;
  }


  /**
   * Combine a HeapNode out of two Nodes. It is assumed that left node is
   * smaller in integer value than right one.
   * @param left
   * @param right
   * @return HeapNode that contains combined value of integer and
   * concatenated ordered alphabet value
   */
  public static HeapNode combineTwoNodes(HeapNode left, HeapNode right) {
    // Combined alphabetical value is ORDERED
    String leftAlphabet = left.getAlphabetValue();
    String rightAlphabet = right.getAlphabetValue();
    String combinedAlphabet = StringUtils.concatenateInOrder(leftAlphabet,
            rightAlphabet);

    int intCombined = left.getIntegerValue() + right.getIntegerValue();
    HeapNode combined = new HeapNode(combinedAlphabet, intCombined);
    return combined;
  }

}
