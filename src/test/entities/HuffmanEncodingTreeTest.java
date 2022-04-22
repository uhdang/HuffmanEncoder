package test.entities;

import main.entities.HeapNode;
import main.entities.HuffmanEncodingTree;
import main.helpers.HuffmanTreeHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;

/**
 * Unit test for HuffmanEncodingTree class
 *
 * @author Jung Woo J Jeong
 * @version 1.0 2022-04-17
 */
public class HuffmanEncodingTreeTest {

  private HeapNode heTreeNode;

  /**
   * Constructor for HuffmanEncodingTreeTest class. In stead of static
   * class, having it non-static with heTreeNode included makdes it easier
   * to make method testing dynamic and flexible
   * @param frequencyTableFilePath
   * @throws FileNotFoundException
   */
  public HuffmanEncodingTreeTest(String frequencyTableFilePath) throws FileNotFoundException {
    HeapNode heTreeNode =
            HuffmanTreeHelper.generateHuffmanTreeStatic(new File(frequencyTableFilePath));
    this.heTreeNode = heTreeNode;
  }

  /**
   * Test method for `encodeSingleLetter` method
   * @return true if encoded string is the same as given letter.
   * @throws FileNotFoundException
   */
  public boolean encodeSingleLetterTest(String inputSingleLetter,
                                        String expectedEncodedValue) {
    String encodedSingleLetter =
            HuffmanEncodingTree.encodeSingleLetter(this.heTreeNode,
            inputSingleLetter);

    return encodedSingleLetter.equals(expectedEncodedValue);
  }

  /**
   * Test method for `encode` method.
   * @return
   * @throws FileNotFoundException
   */
  public boolean encodeTest(String inputString, String expectedEncodedValue) {
    String encodedString = HuffmanEncodingTree.encode(this.heTreeNode,
            inputString);

    return encodedString.equals(expectedEncodedValue);
  }

  /**
   *
   * @param inputEncodedValue
   * @param expectedString
   * @return
   */
  public boolean decodeTest(String inputEncodedValue, String expectedString) {
    String decodedString =
            HuffmanEncodingTree.decode(this.heTreeNode,
                    inputEncodedValue);
    System.out.println(decodedString);
    return expectedString.toUpperCase(Locale.ROOT).equals(decodedString);
  }



  /**
   * Decode an encoded string into an original character
   * @param inputEncodedValue
   * @param expectedOriginalCharacter
   * @return
   */
  public boolean decodeSingleLetterTest(String inputEncodedValue,
                                        String expectedOriginalCharacter) {
    String decodedString =
            HuffmanEncodingTree.decodeSingleLetter(this.heTreeNode,
                    inputEncodedValue);
    return expectedOriginalCharacter.toUpperCase(Locale.ROOT).equals(decodedString);
  }
}
