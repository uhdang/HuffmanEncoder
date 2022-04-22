package main.entities;

import main.utils.FileUtils;
import main.utils.HeapNodeArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * MinHeap class
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class MinHeap {

  private HeapNode[] minHeapTree;
  private File inputFile;
  private int minHeapTreeLastIndex;

  public HeapNode[] getMinHeapTree() {
    return minHeapTree;
  }

  /**
   * Constructor for MinHeap. When initializing, it registers inputFile and
   * initiate NodeArray for minHeapTree with number of lines as a size
   * @param frequencyTableFile
   * @throws FileNotFoundException
   */
  public MinHeap(File frequencyTableFile) throws FileNotFoundException {
    this.inputFile = frequencyTableFile;
    int numLines = FileUtils.getNumLines(frequencyTableFile);
    this.minHeapTree = new HeapNode[numLines];
    this.minHeapTreeLastIndex = -1;
  }

  public HeapNode generateHuffmanTree() {
    while (this.minHeapTreeLastIndex != 0) {
      HeapNode smallestNode = this.removeTopNode();
      HeapNode secondSmallestNode = this.removeTopNode();
      HeapNode[] heapNodeArray = new HeapNode[2];
      heapNodeArray[0] = smallestNode;
      heapNodeArray[1] = secondSmallestNode;

      HeapNode[] heapNodeSortedTreeArray =
              HeapNodeArrayUtils.sortHeapNodeArray(heapNodeArray);
      HeapNode parentNode = HeapNode.combineTwoNodes(heapNodeArray[0],
              heapNodeArray[1]);
      parentNode.setLeftChild(heapNodeSortedTreeArray[0]);
      parentNode.setRightChild(heapNodeSortedTreeArray[1]);
      this.addNodeAtLastPosition(parentNode);
    }
    return this.minHeapTree[0];
  }

  /**
   * Convert string with a format of `alphabet - frequency` to HeapNode.
   * @param alphabetWithFrequency string format of `alphabet - frequency`
   * @return HeapNode instance
   */
  public HeapNode convertToHeapNode(String alphabetWithFrequency) {
    String[] splitted = alphabetWithFrequency.split(" - ");
    String alphabetValue = splitted[0];
    int frequencyValue = Integer.parseInt(splitted[1]);
    return new HeapNode(alphabetValue, frequencyValue);
  }

  /**
   * Generate an unsorted heap node array from input file with string data
   * of alphabet and frequency
   * @return unsorted array with HeapNodes
   */
  public HeapNode[] generateMinHeapArray() throws FileNotFoundException {
    Scanner sc = new Scanner(this.inputFile);

    int indexPointer = 0;

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      if (!Objects.equals(line, "")) {
        HeapNode heapNode = this.convertToHeapNode(line);
        this.addNode(indexPointer, heapNode);
        indexPointer++;
      }
    }
    sc.close();
    return this.minHeapTree;
  }

  /**
   * Add node to a specified index of minHeapArray.
   * Here, this method compares integer value between child and parent and
   * if parent is bigger than child, it swaps, or percolate.
   *
   * NOTE: Tie breaker logic applied when swapping up.
   * @param pointerIndex
   * @param node
   */
  public void addNode(int pointerIndex, HeapNode node) {
    int currentNodeIndex = pointerIndex;
    HeapNode currentNode = node;
    this.minHeapTree[currentNodeIndex] = currentNode;
    this.minHeapTreeLastIndex++;

    boolean needPercolate = true;
    while (needPercolate) {
      int parentPointerIndex = (currentNodeIndex - 1) / 2;
      HeapNode parentNode = this.minHeapTree[parentPointerIndex];

      // compare value and if parent is bigger than the child, swap
      if (parentNode.getIntegerValue() > currentNode.getIntegerValue()) {
        HeapNode temp = parentNode;
        this.minHeapTree[parentPointerIndex] = currentNode;
        this.minHeapTree[currentNodeIndex] = temp;

        currentNodeIndex = parentPointerIndex;
      } else if (parentNode.getIntegerValue() == currentNode.getIntegerValue()) {
        // When tie, tie breaker
        // 1. Smaller Group gets preferred (considered smaller)
        // 2. alphabetical order
        if (parentNode.getAlphabetValue().length() < currentNode.getAlphabetValue().length()) {
        } else if (parentNode.getAlphabetValue().length() > currentNode.getAlphabetValue().length()) {
          HeapNode temp = parentNode;
          this.minHeapTree[parentPointerIndex] = currentNode;
          this.minHeapTree[currentNodeIndex] = temp;
        } else {
          // When length of the Alphabet values are also same, we
          // look at the Alphbetical order
          int alphabeticalOrder =
                  parentNode.getAlphabetValue().compareToIgnoreCase(currentNode.getAlphabetValue());

          if (alphabeticalOrder > 0) {
            HeapNode temp = parentNode;
            this.minHeapTree[parentPointerIndex] = currentNode;
            this.minHeapTree[currentNodeIndex] = temp;
          }
        }
        needPercolate = false;
      } else {
        needPercolate = false;
      }
    }
  }

  /**
   * Add node to the last position
   * @param node
   */
  public void addNodeAtLastPosition(HeapNode node) {
    this.addNode(this.minHeapTreeLastIndex + 1, node);
  }

  /**
   * NEED TO DO REMOVE THE TOP and REORDER the whole tree
   *
   * Remove and return Top Node and sort the tree while putting the last
   * node to the top and percolating down while comparing with child nodes
   * @return Top HeapNode
   */
  public HeapNode removeTopNode() {
    // 1. Remove 0 index heap from minHeapTree
    HeapNode topNode = this.minHeapTree[0];

    // 2. Move the last HeapNode to the first one
    HeapNode lastNode = this.minHeapTree[this.minHeapTreeLastIndex];

    // 3. Put the lastnode into the first spot, fill the last spot with
    // null, and deduct the lastIndex
    this.minHeapTree[0] = lastNode;
    this.minHeapTree[this.minHeapTreeLastIndex] = null;
    this.minHeapTreeLastIndex--;

    // 4. Sort the tree - Look at the left and right child. Swap with the
    // smallest.
    int currentPointerIndex = 0;
    int leftChildPointerIndex = 1;
    int rightChildPointerIndex = 2;

    // Stop when
    // 1. No child node OR the node has become a leaf
    // 2. No child node is smaller than current node
    boolean iterate = true;
    while (iterate) {
      HeapNode currentHeapNode = this.minHeapTree[currentPointerIndex];

      // Case 1. Has Both Left and Right child nodes
      if (this.minHeapTree[leftChildPointerIndex] != null & this.minHeapTree[rightChildPointerIndex] != null) {
        HeapNode leftChildNode = this.minHeapTree[leftChildPointerIndex];
        HeapNode rightChildNode = this.minHeapTree[rightChildPointerIndex];

        // 1. left child is smallest and is smaller than current node ->
        // swap left <-> current
        if ((leftChildNode.getIntegerValue() < rightChildNode.getIntegerValue())
                & (leftChildNode.getIntegerValue() < currentHeapNode.getIntegerValue())) {
          HeapNode tmpNode = currentHeapNode;
          this.minHeapTree[currentPointerIndex] = leftChildNode;
          this.minHeapTree[leftChildPointerIndex] = tmpNode;
          currentPointerIndex = leftChildPointerIndex;
          leftChildPointerIndex = currentPointerIndex * 2 + 1;
          rightChildPointerIndex = currentPointerIndex * 2 + 2;
          // 2. right child is the smallest and smaller than current node
          // -> swap right <-> current
        } else if ((rightChildNode.getIntegerValue() < leftChildNode.getIntegerValue())
                & (rightChildNode.getIntegerValue() < currentHeapNode.getIntegerValue())) {
          HeapNode tmpNode = currentHeapNode;
          this.minHeapTree[currentPointerIndex] = rightChildNode;
          this.minHeapTree[rightChildPointerIndex] = tmpNode;
          currentPointerIndex = rightChildPointerIndex;
          leftChildPointerIndex = currentPointerIndex * 2 + 1;
          rightChildPointerIndex = currentPointerIndex * 2 + 2;
          // 3. [EDGECASE!] When leftChild value and rightChild values are
          // the same
          // while they are smaller than current Node !!!!!!!
        } else if ((leftChildNode.getIntegerValue() == rightChildNode.getIntegerValue())
                & (leftChildNode.getIntegerValue() < currentHeapNode.getIntegerValue())) {
          // Priority 1. Smaller Group gets preferred.
          if (leftChildNode.getAlphabetValue().length() > rightChildNode.getAlphabetValue().length()) {
            HeapNode tmpNode = currentHeapNode;
            this.minHeapTree[currentPointerIndex] = rightChildNode;
            this.minHeapTree[rightChildPointerIndex] = tmpNode;
            currentPointerIndex = rightChildPointerIndex;
            leftChildPointerIndex = currentPointerIndex * 2 + 1;
            rightChildPointerIndex = currentPointerIndex * 2 + 2;
          } else if (leftChildNode.getAlphabetValue().length() < rightChildNode.getAlphabetValue().length()) {
            HeapNode tmpNode = currentHeapNode;
            this.minHeapTree[currentPointerIndex] = leftChildNode;
            this.minHeapTree[leftChildPointerIndex] = tmpNode;
            currentPointerIndex = leftChildPointerIndex;
            leftChildPointerIndex = currentPointerIndex * 2 + 1;
            rightChildPointerIndex = currentPointerIndex * 2 + 2;
          } else {
            // Same Lettersize
            // Priority 2. Alphabetical order
            // When the value is negative (<0), leftChildNode is smaller
            int alphabeticalOrder =
                    leftChildNode.getAlphabetValue().compareToIgnoreCase(rightChildNode.getAlphabetValue());

            if (alphabeticalOrder < 0) {
              HeapNode temp = currentHeapNode;
              this.minHeapTree[currentPointerIndex] = leftChildNode;
              this.minHeapTree[leftChildPointerIndex] = temp;
              currentPointerIndex = leftChildPointerIndex;
              leftChildPointerIndex = currentPointerIndex * 2 + 1;
              rightChildPointerIndex = currentPointerIndex * 2 + 2;
            } else {
              HeapNode tmpNode = currentHeapNode;
              this.minHeapTree[currentPointerIndex] = rightChildNode;
              this.minHeapTree[rightChildPointerIndex] = tmpNode;
              currentPointerIndex = rightChildPointerIndex;
              leftChildPointerIndex = currentPointerIndex * 2 + 1;
              rightChildPointerIndex = currentPointerIndex * 2 + 2;
            }
          }
          // 4. Other cases, leave it as it is. No swap
        } else {
          iterate = false;
        }
        // Case 2. Only left child exist
      } else if (this.minHeapTree[leftChildPointerIndex] != null & this.minHeapTree[rightChildPointerIndex] == null) {
        HeapNode leftChildNode = this.minHeapTree[leftChildPointerIndex];
        if (currentHeapNode.getIntegerValue() > leftChildNode.getIntegerValue()) {
          HeapNode tmpNode = currentHeapNode;
          this.minHeapTree[currentPointerIndex] = leftChildNode;
          this.minHeapTree[leftChildPointerIndex] = tmpNode;
          currentPointerIndex = leftChildPointerIndex;
          leftChildPointerIndex = currentPointerIndex * 2 + 1;
        } else {
          iterate = false;
        }
      } else if (this.minHeapTree[leftChildPointerIndex] == null & this.minHeapTree[rightChildPointerIndex] == null) {
        iterate = false;
      }

      if (leftChildPointerIndex  > this.minHeapTreeLastIndex) {
        iterate = false;
      }
    }

    return topNode;
  }




}

