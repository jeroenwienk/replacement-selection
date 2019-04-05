package nl.jwienk;

public class MinHeap<T extends Comparable> extends Heap<T> {

  /**
   * Constructor to create a min heap with no initial values.
   *
   * @param mClass type of the values
   * @param size   size of the heap
   */
  public MinHeap(Class<T> mClass, int size) {
    super(mClass, size);
  }

  /**
   * Constructor to create a min heap with initial values.
   *
   * @param array the initial values
   */
  public MinHeap(T[] array) {
    super(array);
  }

  /**
   * Percolate a specified index down.
   *
   * @param index the index to percolate down
   */
  @Override
  protected void percolateDown(int index) {
    int leftIndex = this.getLeftChildIndex(index);
    int rightIndex = this.getRightChildIndex(index);

    // find the minimum of the left and right child elements
    int smallerIndex = -1;

    if (leftIndex != -1 && rightIndex != -1) {

      if (this.getElementAtIndex(leftIndex).compareTo(this.getElementAtIndex(rightIndex)) < 0) {
        smallerIndex = leftIndex;
      } else {
        smallerIndex = rightIndex;
      }


    } else if (leftIndex != -1) {
      smallerIndex = leftIndex;
    } else if (rightIndex != -1) {
      smallerIndex = rightIndex;
    }

    // no left or right child no need to percolate down
    if (smallerIndex == -1) {
      return;
    }

    // if smallerIndex element is smaller than index element
    // swap the elements and continue percolate down the value if needed
    if (this.getElementAtIndex(smallerIndex).compareTo(this.getElementAtIndex(index)) < 0) {
      this.swap(smallerIndex, index);
      this.percolateDown(smallerIndex);
    }

  }

  /**
   * Percolate a specified index up.
   *
   * @param index the index to percolate up
   */
  @Override
  protected void percolateUp(int index) {
    int parentIndex = this.getParentIndex(index);

    if (parentIndex != -1 && this.getElementAtIndex(index).compareTo(this.getElementAtIndex(parentIndex)) < 0) {
      this.swap(parentIndex, index);
      this.percolateUp(parentIndex);
    }

  }

}
