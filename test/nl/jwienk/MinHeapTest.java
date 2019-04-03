package nl.jwienk;

import nl.jwienk.exceptions.HeapEmptyException;
import nl.jwienk.exceptions.HeapFullException;

import static org.junit.Assert.*;

public class MinHeapTest {

  private MinHeap<Integer> minHeap;

  @org.junit.Before
  public void setUp() throws Exception {
    minHeap = new MinHeap<>(Integer.class, 20);
  }

  @org.junit.After
  public void tearDown() throws Exception {
  }

  @org.junit.Test
  public void insert() throws HeapFullException, HeapEmptyException {

    minHeap.insert(2);
    minHeap.insert(6);
    minHeap.insert(3);
    minHeap.insert(4);
    minHeap.insert(5);

    assertEquals((int) minHeap.getHighestPriority(), 2);
    assertEquals(minHeap.getHeapSize(), 5);
    assertEquals(minHeap.getMaxHeapSize(), 20);
  }

  @org.junit.Test
  public void removeHighestPriority() throws HeapFullException, HeapEmptyException {
    minHeap.insert(2);
    minHeap.insert(6);
    minHeap.insert(3);
    minHeap.insert(4);
    minHeap.insert(5);

    assertEquals((int) minHeap.removeHighestPriority(), 2);
    assertEquals((int) minHeap.getHighestPriority(), 3);
  }

  @org.junit.Test
  public void getHighestPriority() throws HeapFullException, HeapEmptyException {
    minHeap.insert(2);
    minHeap.insert(6);
    minHeap.insert(3);
    minHeap.insert(4);
    minHeap.insert(5);


    assertEquals((int) minHeap.getHighestPriority(), 2);
    assertEquals((int) minHeap.removeHighestPriority(), 2);
    assertEquals((int) minHeap.getHighestPriority(), 3);
  }

  @org.junit.Test
  public void getHeapSize() throws HeapFullException, HeapEmptyException {
    assertEquals(minHeap.getHeapSize(), 0);
    minHeap.insert(2);
    minHeap.insert(6);
    assertEquals(minHeap.getHeapSize(), 2);
    minHeap.removeHighestPriority();
    assertEquals(minHeap.getHeapSize(), 1);
  }

  @org.junit.Test
  public void isEmpty() throws HeapFullException {
    assertTrue(minHeap.isEmpty());
    minHeap.insert(1);
    assertFalse(minHeap.isEmpty());
  }

  @org.junit.Test
  public void isFull() throws HeapFullException, HeapEmptyException {
    for (int i = 0; i < minHeap.getMaxHeapSize(); i++) {
      minHeap.insert(i);
    }

    assertTrue(minHeap.isFull());
    minHeap.removeHighestPriority();
    assertFalse(minHeap.isFull());
  }

  @org.junit.Test(expected = IndexOutOfBoundsException.class)
  public void getElementAtIndex() throws HeapFullException {
    minHeap.insert(2);
    minHeap.insert(6);
    minHeap.insert(3);
    minHeap.insert(4);
    minHeap.insert(5);

    assertEquals((int) minHeap.getElementAtIndex(0), 2);
    assertNotEquals((int) minHeap.getElementAtIndex(0), 3);

    minHeap.getElementAtIndex(20);
  }

  @org.junit.Test
  public void getLeftChildIndex() throws HeapFullException, HeapEmptyException {
    minHeap.insert(2);
    minHeap.insert(6);
    minHeap.insert(3);

    assertEquals(minHeap.getLeftChildIndex(0), 1);

    minHeap.removeHighestPriority();
    minHeap.removeHighestPriority();

    assertEquals(minHeap.getLeftChildIndex(0), -1);
  }

  @org.junit.Test
  public void getRightChildIndex() throws HeapFullException, HeapEmptyException {
    minHeap.insert(2);
    minHeap.insert(6);
    minHeap.insert(3);

    assertEquals(minHeap.getRightChildIndex(0), 2);

    minHeap.removeHighestPriority();
    minHeap.removeHighestPriority();

    assertEquals(minHeap.getRightChildIndex(0), -1);
  }

  @org.junit.Test
  public void getParentIndex() throws HeapFullException, HeapEmptyException {
    minHeap.insert(2);
    minHeap.insert(6);
    minHeap.insert(3);

    assertEquals(minHeap.getParentIndex(1), 0);
    assertEquals(minHeap.getParentIndex(0), 0);
  }

  @org.junit.Test
  public void percolateDown() throws HeapEmptyException {
    Integer[] arr = new Integer[]{6, 4, 7, 9, 1, 5};
    MinHeap heap = new MinHeap<>(arr);

    heap.swap(0, 5);
    assertEquals((int) heap.getHighestPriority(), 7);

    heap.percolateDown(0);

    assertEquals((int) heap.getElementAtIndex(4), 7);
  }

  @org.junit.Test
  public void percolateUp() throws HeapEmptyException {
    Integer[] arr = new Integer[]{6, 4, 7, 9, 1, 5};
    MinHeap heap = new MinHeap<>(arr);

    heap.swap(0, 5);

    assertEquals((int) heap.getHighestPriority(), 7);

    heap.percolateUp(5);

    assertEquals((int) heap.getHighestPriority(), 1);
  }


}