package nl.jwienk;

import nl.jwienk.exceptions.HeapEmptyException;
import nl.jwienk.exceptions.HeapFullException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DynDSHeapTest {

  private DynDSHeap<Integer> heap;

  @Before
  public void setUp() throws Exception {
    heap = new DynDSHeap<>(Integer.class, 4);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void insertIntoDeadSpace() throws HeapFullException, HeapEmptyException {

    heap.insert(3);
    heap.insert(6);
    heap.insert(2);
    heap.insert(9);

    heap.removeHighestPriority();
    heap.insertIntoDeadSpace(1);
    assertEquals(3, heap.getHeapSize());
    assertEquals(3, heap.getMaxHeapSize());
    assertEquals(1, (int) heap.getElementAtIndex(3));
    assertEquals(1, heap.getDeadSpace());

    heap.removeHighestPriority();
    heap.insertIntoDeadSpace(2);
    assertEquals(2, heap.getHeapSize());
    assertEquals(2, heap.getMaxHeapSize());
    assertEquals(2, (int) heap.getElementAtIndex(2));
    assertEquals(2, heap.getDeadSpace());

  }

  @Test
  public void buildHeap() throws HeapFullException, HeapEmptyException {

    heap.insert(3);
    heap.insert(6);
    heap.insert(2);
    heap.insert(9);

    heap.removeHighestPriority();
    heap.insertIntoDeadSpace(1);
    assertEquals(3, heap.getHeapSize());
    assertEquals(3, heap.getMaxHeapSize());
    assertEquals(1, (int) heap.getElementAtIndex(3));
    assertEquals(1, heap.getDeadSpace());

    heap.removeHighestPriority();
    heap.insertIntoDeadSpace(2);
    assertEquals(2, heap.getHeapSize());
    assertEquals(2, heap.getMaxHeapSize());
    assertEquals(2, (int) heap.getElementAtIndex(2));
    assertEquals(2, heap.getDeadSpace());

    heap.buildHeap();
    assertEquals(1, (int) heap.removeHighestPriority());
    assertEquals(2, (int) heap.removeHighestPriority());

  }
}