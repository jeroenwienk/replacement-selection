package nl.jwienk;

import nl.jwienk.exceptions.HeapEmptyException;
import nl.jwienk.exceptions.HeapFullException;
import nl.jwienk.utils.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MainTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void sheetsExample() throws HeapFullException, HeapEmptyException {
    Integer[] arr = {81, 94, 11, 96, 12, 35, 17, 99, 28, 58, 41, 75, 15};
    Scanner in = new Scanner(Arrays.toString(arr).replaceAll("[^a-zA-Z0-9\\s]", ""));
    int heapSize = 3;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);
    String output = stringWriter.toString();

    assertEquals("" +
        "RUN0= 11 81 94 96 \n" +
        "RUN1= 12 17 28 35 41 58 75 99 \n" +
        "RUN2= 15 ", output);

    assertEquals(4.333333333333333, result.getAverageRunSize(), 0);

  }

  @Test
  public void immediateDeadSpace() throws HeapFullException, HeapEmptyException {
    Integer[] arr = {81, 94, 11, 9, 8};
    Scanner in = new Scanner(Arrays.toString(arr).replaceAll("[^a-zA-Z0-9\\s]", ""));
    int heapSize = 4;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);
    String output = stringWriter.toString();

    assertEquals("" +
        "RUN0= 9 11 81 94 \n" +
        "RUN1= 8 ", output);

    assertEquals(2.5, result.getAverageRunSize(), 0);

  }

  @Test
  public void longRun() throws HeapFullException, HeapEmptyException {
    Integer[] arr = {81, 94, 11, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    Scanner in = new Scanner(Arrays.toString(arr).replaceAll("[^a-zA-Z0-9\\s]", ""));
    int heapSize = 4;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);
    String output = stringWriter.toString();

    assertEquals("" +
        "RUN0= 9 10 11 11 12 13 14 15 16 17 18 19 20 21 22 23 81 94 ", output);

    assertEquals(18.0, result.getAverageRunSize(), 0);

  }

  @Test
  public void fromFile() throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Scanner in = new Scanner(new File("static-input-small.txt"));
    int heapSize = 8;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);
    String output = stringWriter.toString();

    assertEquals("" +
        "RUN0= 1 9 11 14 25 32 34 44 47 57 60 67 72 77 89 95 99 \n" +
        "RUN1= 13 16 17 20 23 46 51 52 66 77 82 83 87 90 93 94 99 \n" +
        "RUN2= 3 3 13 21 27 28 42 46 51 55 77 78 86 96 \n" +
        "RUN3= 14 23 24 27 32 35 36 41 46 51 52 55 56 74 81 94 \n" +
        "RUN4= 6 7 20 23 26 30 33 33 41 44 56 65 68 78 84 86 88 88 94 96 98 \n" +
        "RUN5= 17 35 35 35 35 41 46 54 55 77 78 87 94 99 \n" +
        "RUN6= 3 4 9 20 29 30 39 43 49 49 58 60 66 78 89 95 96 \n" +
        "RUN7= 11 12 13 20 37 38 41 45 60 60 62 66 72 92 \n" +
        "RUN8= 3 14 22 36 48 53 54 57 65 72 74 78 89 94 \n" +
        "RUN9= 2 3 3 18 35 36 40 51 64 82 91 94 95 \n" +
        "RUN10= 6 14 14 21 23 25 32 39 51 53 55 59 62 70 72 75 79 88 89 \n" +
        "RUN11= 2 4 9 15 41 42 43 50 55 56 57 58 60 87 89 \n" +
        "RUN12= 4 11 11 20 25 25 38 50 84 ", output);

    assertEquals(15.384615384615385, result.getAverageRunSize(), 0);

  }

  @Test
  public void checkAverage3() throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Scanner in = new Scanner(new File("static-input-large.txt"));
    int heapSize = 3;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);

    double high = 7;
    double low = 5;
    assertThat(result.getAverageRunSize(), allOf(greaterThan(low), lessThan(high)));
  }

  @Test
  public void checkAverage4() throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Scanner in = new Scanner(new File("static-input-large.txt"));
    int heapSize = 4;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);

    double high = 9;
    double low = 7;
    assertThat(result.getAverageRunSize(), allOf(greaterThan(low), lessThan(high)));
  }

  @Test
  public void checkAverage8() throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Scanner in = new Scanner(new File("static-input-large.txt"));
    int heapSize = 8;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);

    double high = 17.0;
    double low = 15.0;
    assertThat(result.getAverageRunSize(), allOf(greaterThan(low), lessThan(high)));
  }

  @Test
  public void checkAverage16() throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Scanner in = new Scanner(new File("static-input-large.txt"));
    int heapSize = 16;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);

    double high = 33.0;
    double low = 31.0;
    assertThat(result.getAverageRunSize(), allOf(greaterThan(low), lessThan(high)));
  }

  @Test
  public void checkAverage32() throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Scanner in = new Scanner(new File("static-input-large.txt"));
    int heapSize = 32;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);

    double high = 65.0;
    double low = 63.0;
    assertThat(result.getAverageRunSize(), allOf(greaterThan(low), lessThan(high)));
  }

  @Test
  public void checkAverage64() throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Scanner in = new Scanner(new File("static-input-large.txt"));
    int heapSize = 64;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);

    double high = 129.0;
    double low = 127.0;
    assertThat(result.getAverageRunSize(), allOf(greaterThan(low), lessThan(high)));
  }

  @Test
  public void checkAverage128() throws HeapFullException, HeapEmptyException, FileNotFoundException {
    Scanner in = new Scanner(new File("static-input-large.txt"));
    int heapSize = 128;

    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);

    Result result = Main.replacementSelection(in, out, heapSize);

    double high = 257.0;
    double low = 255.0;
    assertThat(result.getAverageRunSize(), allOf(greaterThan(low), lessThan(high)));
  }
}