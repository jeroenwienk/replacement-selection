package nl.jwienk;

import nl.jwienk.exceptions.HeapEmptyException;
import nl.jwienk.exceptions.HeapFullException;
import nl.jwienk.utils.Helpers;
import nl.jwienk.utils.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Helpers.generateFile(20000, 100);

    File inFile = new File("input.txt");
    File outFile = new File("output.txt");

    Scanner in = new Scanner(inFile);
    PrintWriter out = new PrintWriter(outFile);

    Result result = replacementSelection(in, out, 3);
    result.print();
  }

  /**
   * Replacement selection algorithm
   *
   * @param in       Scanner with the input
   * @param out      the PrintWriter to output to
   * @param heapSize the required heap size
   * @return Result containing the average runSize
   */
  public static Result replacementSelection(Scanner in, PrintWriter out, int heapSize)
      throws HeapEmptyException, HeapFullException {
    int runCount = 0;
    int runSize = 0;

    ArrayList<Integer> runSizes = new ArrayList<>();
    DynDSHeap<Integer> heap = new DynDSHeap<>(Integer.class, heapSize);

    // initially fill the heap until it is full
    while (!heap.isFull() && in.hasNext()) {
      heap.insert(in.nextInt());
    }

    out.write(String.format("RUN%d= ", runCount));
    runCount++;

    while (in.hasNext()) {
      int smallest = heap.removeHighestPriority();
      out.write(smallest + " ");
      runSize++;

      int nextElement = in.nextInt();

      if (nextElement >= smallest) {
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

    // output the last values as a seperate run
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
    return new Result(average, heapSize);
  }
}
