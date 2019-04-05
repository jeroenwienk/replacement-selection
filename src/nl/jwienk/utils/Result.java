package nl.jwienk.utils;

public class Result {

  private double averageRunSize;
  private int inputSize;
  private int heapSize;

  public Result(double averageRunSize, int heapSize, int inputSize) {
    this.averageRunSize = averageRunSize;
    this.heapSize = heapSize;
    this.inputSize = inputSize;
  }

  public double getAverageRunSize() {
    return averageRunSize;
  }

  public void setAverageRunSize(double averageRunSize) {
    this.averageRunSize = averageRunSize;
  }

  public int getHeapSize() {
    return heapSize;
  }

  public void setHeapSize(int heapSize) {
    this.heapSize = heapSize;
  }

  public int getInputSize() {
    return inputSize;
  }

  public void print() {
    System.out.println("inputSize= " + inputSize);
    System.out.println("heapSize= " + heapSize);
    System.out.println("Average runSize= " + averageRunSize);
  }
}
