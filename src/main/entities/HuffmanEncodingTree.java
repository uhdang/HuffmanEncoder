package main.entities;

/**
 * HuffmanEncodingTree class with methods
 * for encoding and decoding
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-21
 */
public class HuffmanEncodingTree {


  /**
   * Encode method for non-single / longer string
   * @param heTree
   * @param stringValue
   * @return
   */
  public static String encode(HeapNode heTree, String stringValue) {

    String wholeString = "";
    for (int i = 0; i < stringValue.length(); i++) {
      String letter = String.valueOf(stringValue.charAt(i));
      String encodedSingleLetter =
              HuffmanEncodingTree.encodeSingleLetter(heTree,
              letter);
      wholeString += encodedSingleLetter;
    }

    return wholeString;
  }

  /**
   * Single Letter Encoding Method
   * @return
   */
  public static String encodeSingleLetter(HeapNode heTreeNode,
                                          String singleLetter) {

    HeapNode currentNode = heTreeNode;
    String encoding = "";
    String capLetter = singleLetter.toUpperCase();

    while (!currentNode.isLeafNode()) {
      if (currentNode.getLeftChild().getAlphabetValue().contains(capLetter)) {
        encoding += "0";
        currentNode = currentNode.getLeftChild();
      } else if (currentNode.getRightChild().getAlphabetValue().contains(capLetter)) {
        encoding += "1";
        currentNode = currentNode.getRightChild();
      } else {
        // Nothing to be matching - High possibility that the singleLetter
        // variable is not existing in frequency table
        encoding += singleLetter;
        break;
      }
    }
    return encoding;
  }

  /**
   *
   * @param heTreeNode
   * @param encodedString
   * @return
   */
  public static String decode(HeapNode heTreeNode, String encodedString) {
    HeapNode currentNode = heTreeNode;
    String output = "";

    for (int i = 0; i < encodedString.length(); i++) {
      String digit = String.valueOf(encodedString.charAt(i));

      if (digit.equals("0")) {
        currentNode = currentNode.getLeftChild();
      } else if (digit.equals("1")) {
        currentNode = currentNode.getRightChild();
      } else {
        output += digit;
        currentNode = heTreeNode;
      }

      if (currentNode.isLeafNode()) {
        output += currentNode.getAlphabetValue();
        currentNode = heTreeNode;
      }
    }
    return output;

  }

  /**
   * Decoder for a single letter. This is only used when we can assume
   * input encoded string is exactly one character. This is not used in a
   * word or a sentence processing.
   * @param heTreeNode
   * @param encodedString
   * @return Original Character
   */
  public static String decodeSingleLetter(HeapNode heTreeNode,
                                          String encodedString) {
    HeapNode currentNode = heTreeNode;

    for (int i = 0; i < encodedString.length(); i++) {
      String digit = String.valueOf(encodedString.charAt(i));
      if (digit.equals("0")) {
        currentNode = currentNode.getLeftChild();
      } else if (digit.equals("1")) {
        currentNode = currentNode.getRightChild();
      } else {
        System.out.println("Not 0 nor 1 - " + digit);
      }
    }
    if (!currentNode.isLeafNode()) {
      System.out.println("Reached the end of encodedString but the node is " +
              "not a leaf node. There is something wrong");
    }
    return currentNode.getAlphabetValue();
  }
}
