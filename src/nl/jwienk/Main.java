package nl.jwienk;

import nl.jwienk.exceptions.HeapEmptyException;
import nl.jwienk.exceptions.HeapFullException;
import nl.jwienk.utils.Helpers;
import nl.jwienk.utils.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Helpers.generateFile(20000, 100);

    File inFile = new File("input.txt");
    File outFile = new File("output.txt");

    Scanner in = new Scanner(inFile);
    PrintWriter out = new PrintWriter(outFile);

    Result result = replacementSelection(in, out, 4, Integer.class);
    result.print();

  }

  /**
   * Replacement selection algorithm
   *
   * @param in       Scanner with the input
   * @param out      the PrintWriter to output to
   * @param heapSize the required heap size
   * @param mClass   the type of the data
   * @return Result containing the average runSize
   */
  public static <T extends Comparable> Result replacementSelection(Scanner in, PrintWriter out, int heapSize, Class<T> mClass)
      throws HeapEmptyException, HeapFullException {
    int runCount = 0;
    int runSize = 0;
    int nrOfElementsRead = 0;

    ArrayList<Integer> runSizes = new ArrayList<>();
    DynDSHeap<T> heap = new DynDSHeap<>(mClass, heapSize);

    // initially fill the heap until it is full
    while (!heap.isFull() && in.hasNext()) {
      nrOfElementsRead++;
      heap.insert(readNext(in, mClass));
    }

    out.write(String.format("RUN%d= ", runCount));
    runCount++;

    while (in.hasNext()) {
      nrOfElementsRead++;
      T smallest = heap.removeHighestPriority();
      out.write(smallest + " ");
      runSize++;

      T nextElement = readNext(in, mClass);

      if (nextElement.compareTo(smallest) >= 0) {
        heap.insert(nextElement);
      } else {
        heap.insertIntoDeadSpace(nextElement);
      }

      // end of the current run, rebuild the heap from the deadSpace
      // and continue reading input
      if (heap.isEmpty()) {
        runSizes.add(runSize);
        out.write(String.format("\nRUN%d= ", runCount));
        runCount++;
        runSize = 0;
        heap.buildHeap();
      }

    }

    // no more input, output the last heap values
    while (!heap.isEmpty()) {
      out.write(heap.removeHighestPriority() + " ");
      runSize++;
    }

    runSizes.add(runSize);
    runSize = 0;


    // rebuild the heap from the deadSpace if any
    heap.buildHeap();

    // output the last values as a separate run
    if (!heap.isEmpty()) {
      out.write(String.format("\nRUN%d= ", runCount));
      while (!heap.isEmpty()) {
        out.write(heap.removeHighestPriority() + " ");
        runSize++;
      }
      runSizes.add(runSize);
    }

    int sum = 0;
    for (int size : runSizes) {
      sum += size;
    }

    // calculate the average runSize should about equal 2 * heapSize
    double average = sum / (double) runSizes.size();

    out.close();
    return new Result(average, heapSize, nrOfElementsRead);
  }

  /**
   * Read a next value from in based on the type
   * @param in input Scanner
   * @param mClass current data type of the input
   * @return next read value
   */
  public static <T> T readNext(Scanner in, Class<T> mClass) {
    try {
      if (mClass == Integer.class)
        return mClass.cast(in.nextInt());
      if (mClass == String.class)
        return mClass.cast(in.next());
      if (mClass == Double.class)
        return mClass.cast(in.nextDouble());
      if (mClass == Float.class)
        return mClass.cast(in.nextFloat());
    } catch (InputMismatchException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void run() throws FileNotFoundException, HeapFullException, HeapEmptyException {
    ArrayList<Result> results = new ArrayList<>();
    for (int heapSize = 4; heapSize <= 512; heapSize = heapSize * 2) {
      for (int i = 1; i <= 10; i++) {
        Helpers.generateFile(1000, 100);
        File inFile = new File("input.txt");
        File outFile = new File("output.txt");

        Scanner in = new Scanner(inFile);
        PrintWriter out = new PrintWriter(outFile);

        Result result = replacementSelection(in, out, heapSize, Integer.class);
        results.add(result);
      }
    }
    Helpers.resultsToFile(results);
  }

}
