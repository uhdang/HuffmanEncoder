package main.helpers;

import main.entities.HeapNode;
import main.entities.HeapStack;
import main.entities.HuffmanEncodingTree;
import main.entities.MinHeap;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * HuffmanTree Helper function. First layer of class that transmit an
 * intended logic into a functional methods
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-21
 */
public class HuffmanTreeHelper {
  private String frequencyTableFilePath;
  private String clearTextFilePath;
  private String encodedTextFilePath;
  private String preorderString;

  /**
   * Constructor for HuffmanTreeHelper
   * @param frequencyTableFilePath String
   * @param clearTextFilePath String
   * @param encodedTextFilePath String
   */
  public HuffmanTreeHelper(String frequencyTableFilePath, String clearTextFilePath
          , String encodedTextFilePath) {
    this.frequencyTableFilePath = frequencyTableFilePath;
    this.clearTextFilePath = clearTextFilePath;
    this.encodedTextFilePath = encodedTextFilePath;
    this.preorderString = "";
  }

  /**
   * Generate a huffman tree while going through generating minHeap.
   * @return HeapNode with pointers that leads to huffman tree
   * @throws FileNotFoundException
   */
  public HeapNode generateHuffmanTree() throws FileNotFoundException {
    File frequencyTableFile = new File(this.frequencyTableFilePath);
    MinHeap minHeapInstance = new MinHeap(frequencyTableFile);
    minHeapInstance.generateMinHeapArray();
    return minHeapInstance.generateHuffmanTree();
  }

  public static HeapNode generateHuffmanTreeStatic(File frequencyTableFile) throws FileNotFoundException {
    MinHeap minHeapInstance = new MinHeap(frequencyTableFile);
    minHeapInstance.generateMinHeapArray();
    return minHeapInstance.generateHuffmanTree();
  }

  /**
   * Printout HuffmanTree in Preorder Traversal and saves it in a file
   * @throws IOException
   */
  public void preorderTraversal(HeapNode heTree) {
    if (heTree != null) {
      this.preorderString += heTree.getAlphabetValue() + ": " + heTree.getIntegerValue() + ", ";
      this.preorderTraversal(heTree.getLeftChild());
      this.preorderTraversal(heTree.getRightChild());
    }
  }

  /**
   * Generate a string of preorder traversal and display.
   * Also writes it in a file
   * @param heTree
   * @throws IOException
   */
  public void generateHuffmanTreePreorderTraversal(HeapNode heTree) throws IOException {
    this.preorderTraversal(heTree);

    String outputString =
            "[Huffman Encoding Tree in preorder]\n\n" + this.preorderString + "\n\n";
    String outputFile = "outputs/HuffmanTree_preorder_traversal.txt";
    BufferedWriter writer =
            new BufferedWriter(new FileWriter(outputFile));
    writer.write(outputString);
    writer.close();
    System.out.println(outputString);

  }

  /**
   * Create a file that lays out encoded letter in alphabet and Print it out
   */
  public static void displayLetterEncodeValuesFromFrequencyTable(HeapNode heTreeNode, String frequencyTableFilePath) throws IOException {
    Scanner sc = new Scanner(new File(frequencyTableFilePath));
    String output = "[Encode letter values from " +
            "FrequencyTable]\n";


    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      if (!Objects.equals(line, "")) {
        String[] splitted = line.split(" - ");
        String alphabetValue = splitted[0];
        String encodedValue =
                HuffmanEncodingTree.encodeSingleLetter(heTreeNode,
                        alphabetValue);
        output += alphabetValue + ": " + encodedValue + "\n";
      }
    }
    sc.close();
    String alphabetEncodedOutputFile = "outputs/alphabetEncoded.txt";
    BufferedWriter writer =
            new BufferedWriter(new FileWriter(alphabetEncodedOutputFile));
    writer.write(output);
    writer.close();

    System.out.println(output);
  }

  /**
   *
   * @param heTree
   * @param clearTextFilePath
   * @throws IOException
   */
  public static void displayEncodedValues(HeapNode heTree,
                                          String clearTextFilePath,
                                          String outputFilePath) throws IOException {

    Scanner sc = new Scanner(new File(clearTextFilePath));
    String output = "[Encoding Outputs from " + clearTextFilePath + "]\n\n";

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      if (!Objects.equals(line, "")) {

        long startNs = System.nanoTime();
        String encoded = HuffmanEncodingTree.encode(heTree, line);
        long endNs = System.nanoTime();

        output += "Input: " + line + " | Output:  " + encoded +
                " [ProcessTime: " + (endNs - startNs) + " ns]\n";
      }
    }
    sc.close();
    BufferedWriter writer =
            new BufferedWriter(new FileWriter(outputFilePath));
    writer.write(output);
    writer.close();

    System.out.println(output);
  }

  public static void displayDecodedValues(HeapNode heTree,
                                          String encodedTextFilePath,
                                          String outputFilePath) throws IOException {
    Scanner sc = new Scanner(new File(encodedTextFilePath));
    String output = "[Decoded Outputs from " + encodedTextFilePath + "]\n\n";

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      if (!Objects.equals(line, "")) {

        long startNs = System.nanoTime();
        String encoded = HuffmanEncodingTree.decode(heTree, line);
        long endNs = System.nanoTime();

        output += "Input: " + line + " | Output:  " + encoded +
                " [ProcessTime: " + (endNs - startNs) + " ns]\n";
      }
    }
    sc.close();
    BufferedWriter writer =
            new BufferedWriter(new FileWriter(outputFilePath));
    writer.write(output);
    writer.close();

    System.out.println(output);

  }

}
