package nl.jwienk;

import nl.jwienk.exceptions.HeapEmptyException;
import nl.jwienk.exceptions.HeapFullException;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class Heap<T extends Comparable> {

  private T[] array;
  private int heapSize = 0;
  private int maxHeapSize = 0;

  /**
   * Constructor to create a heap with no initial values.
   *
   * @param mClass type of the values
   * @param size   size of the heap
   */
  public Heap(Class<T> mClass, int size) {
    this.array = (T[]) Array.newInstance(mClass, size);
    this.maxHeapSize = array.length;
  }

  /**
   * Constructor to create a heap with initial values.
   *
   * @param array the initial values
   */
  public Heap(T[] array) {
    this.array = array;
    this.heapSize = array.length;
    this.maxHeapSize = array.length;
    buildHeap();
  }

  /**
   * Swap the values of two indexes.
   *
   * @param index1 first index to swap
   * @param index2 second index to swap
   */
  public void swap(int index1, int index2) {
    T tempValue = array[index1];
    array[index1] = array[index2];
    array[index2] = tempValue;
  }

  /**
   * Insert an element into the heap.
   *
   * @param element the element to insert
   * @throws HeapFullException
   */
  public void insert(T element) throws HeapFullException {
    if (heapSize >= maxHeapSize) {
      throw new HeapFullException();
    }

    array[heapSize] = element;
    percolateUp(heapSize);
    heapSize++;
  }

  /**
   * Remove the element with the highest priority.
   *
   * @return the element with the highest priority
   * @throws HeapEmptyException
   */
  public T removeHighestPriority() throws HeapEmptyException {
    T element = getHighestPriority();

    array[0] = array[heapSize - 1];
    heapSize--;
    percolateDown(0);

    return element;
  }

  /**
   * Get the element with the highest priority.
   *
   * @return the element with the highest priority
   * @throws HeapEmptyException
   */
  public T getHighestPriority() throws HeapEmptyException {
    if (heapSize == 0) {
      throw new HeapEmptyException();
    }

    return array[0];
  }

  /**
   * Build the heap so that it satisfies the heap property.
   */
  public void buildHeap() {
    int index = getParentIndex(heapSize - 1);
    while (index >= 0) {
      percolateDown(index);
      index--;
    }
  }

  /**
   * Not needed for this exercise but applies a heapSort
   * to the current heap.
   */
  public void heapSort() {
    buildHeap();

    int endIndex = heapSize - 1;
    while (endIndex > 0) {
      swap(0, endIndex);
      endIndex--;
      heapSize--;
      percolateDown(0);
    }
    System.out.println(Arrays.toString(array));
  }

  /**
   * Decrease the maximum allowed heap size.
   */
  public void decreaseMaxHeapSize() {
    this.maxHeapSize--;
  }

  /**
   * Get heap size.
   *
   * @return Gets the current size of the heap
   */
  public int getHeapSize() {
    return heapSize;
  }

  /**
   * Get max heap size.
   *
   * @return Gets the current max size of the heap
   */
  public int getMaxHeapSize() {
    return maxHeapSize;
  }

  public void setHeapSize(int size) {
    this.heapSize = size;
  }

  public void setMaxHeapSize(int size) {
    this.maxHeapSize = size;
  }

  /**
   * Check if the heap is empty
   *
   * @return true if empty false otherwise
   */
  public boolean isEmpty() {
    return heapSize == 0;
  }

  /**
   * Check to see if the heap is currently full.
   *
   * @return true if full false otherwise
   */
  public boolean isFull() {
    return heapSize == maxHeapSize;
  }

  public T[] getArray() {
    return array;
  }

  /**
   * Get an element at the specified index.
   *
   * @param index the index to get
   * @return element at requested index
   */
  public T getElementAtIndex(int index) {
    return array[index];
  }

  /**
   * Get the index of the left child of the current index
   *
   * @param index the index to check
   * @return index of the left child or -1 if no left child
   */
  public int getLeftChildIndex(int index) {
    int leftChildIndex = 2 * index + 1;

    if (leftChildIndex >= heapSize) {
      return -1; // no left child
    }

    return leftChildIndex;
  }

  /**
   * Get the index of the right child of the current index
   *
   * @param index the index to check
   * @return index of the right child or -1 if no right child
   */
  public int getRightChildIndex(int index) {
    int rightChildIndex = 2 * index + 2;

    if (rightChildIndex >= heapSize) {
      return -1; // no right child
    }

    return rightChildIndex;
  }

  /**
   * Get the index of the parent of the current index
   *
   * @param index the index to check
   * @return index of the parent or -1 if index out of bounds
   */
  public int getParentIndex(int index) {
    if (index < 0 || index > heapSize) {
      return -1;
    }

    return (index - 1) / 2;
  }

  protected abstract void percolateDown(int index);

  protected abstract void percolateUp(int index);

  /**
   * Display a readable representation of the current heap.
   */
  public void displayHeap() {
    int nBlanks = 32;
    int itemsPerRow = 1;
    int column = 0;
    int j = 0; // current item

    String dots = "...............................";
    System.out.println(dots + dots);
    while (heapSize > 0) {
      if (column == 0) {
        for (int k = 0; k < nBlanks; k++) {
          System.out.print(" ");
        }
      }
      System.out.print(array[j]);
      j++;
      if (j == heapSize) {
        break;
      }

      column++;
      // end of row
      if (column == itemsPerRow) {
        nBlanks = nBlanks / 2; // half the blanks
        itemsPerRow = itemsPerRow * 2;     // twice the items
        column = 0;
        System.out.println();
      } else {
        for (int k = 0; k < nBlanks * 2; k++) {
          System.out.print(" ");
        }
      }

    }
    System.out.println("\n" + dots + dots);


  }

}
