package nl.jwienk.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public final class Helpers {
  private Helpers() {
  }

  /**
   * Generate a file with random integer numbers
   * @param count number of integers
   * @param range the specified range for the numbers: 1 - range
   */
  public static void generateFile(int count, int range) {
    try {
      PrintWriter out = new PrintWriter(new File("input.txt"));
      Random rand = new Random();


      for (int i = 0; i < count; i++) {
        int number = rand.nextInt(range) + 1;

        if (i != 0 && i % 10 == 0) {
          out.println();
        }

        out.print(number + " ");
      }

      out.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

}
