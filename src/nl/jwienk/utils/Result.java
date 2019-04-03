package nl.jwienk.utils;

public class Result {

  private double averageRunSize;
  private int heapSize;

  public Result(double averageRunSize, int heapSize) {
    this.averageRunSize = averageRunSize;
    this.heapSize = heapSize;
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

  public void print() {
    System.out.println("heapSize= " + heapSize);
    System.out.println("Average runSize= " + averageRunSize);
  }
}
