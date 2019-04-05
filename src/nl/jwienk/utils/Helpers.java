package nl.jwienk.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public final class Helpers {
  private Helpers() {
  }

  /**
   * Generate a file with random integer numbers
   *
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

  public static void resultsToFile(ArrayList<Result> results) throws FileNotFoundException {
    PrintWriter pw = new PrintWriter(new File("results.txt"));
    StringBuilder sb = new StringBuilder();

    for (Result result : results) {
      sb.append(result.getInputSize());
      sb.append(",");
      sb.append(result.getHeapSize());
      sb.append(",");
      sb.append(result.getAverageRunSize());
      sb.append("\n");
    }

    pw.write(sb.toString());
    pw.close();
  }

}
