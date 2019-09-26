package com.ecommerce.albertsons.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/** RandomNumberGenerator class.
 *  used to create dummy CIC Source file for the project.
 */
public class RandomNumberGenerator {
  /** Method for creating data.
   */
  
  public static void main(String[] args) throws IOException {
    FileWriter fw = new FileWriter("Sample_Input-300000.txt");
    Random random = new Random();
    try {
      for (int count = 0; count < 300000; count++) {
        int i = random.nextInt() & Integer.MAX_VALUE;
        fw.write(i + "\n");
      }
      System.out.println("Input Feed for CIC Generated");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      fw.close();
    }
  }
}
