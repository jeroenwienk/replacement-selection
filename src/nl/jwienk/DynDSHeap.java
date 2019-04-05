package nl.jwienk;

public class DynDSHeap<T extends Comparable> extends MinHeap<T> {

  private int deadSpace = 0;

  /**
   * Constructor to create a dynamic deadSpace heap with no initial values.
   *
   * @param mClass type of the values
   * @param size   size of the heap
   */
  public DynDSHeap(Class<T> mClass, int size) {
    super(mClass, size);
  }

  /**
   * Insert an element into the deadSpace of this heap.
   *
   * @param element
   */
  public void insertIntoDeadSpace(T element) {
    this.deadSpace++;
    this.decreaseMaxHeapSize();
    this.getArray()[this.getMaxHeapSize()] = element;
  }

  /**
   * Reset the deadSpace to 0.
   */
  public void resetDeadSpace() {
    this.deadSpace = 0;
  }

  /**
   * Get the current size of the deadSpace.
   *
   * @return the size of the deadSpace
   */
  public int getDeadSpace() {
    return this.deadSpace;
  }

  /**
   * Build the heap so that it satisfies the heap property.
   * This is based on the size of the deadSpace.
   */
  public void buildHeap() {
    this.setHeapSize(this.deadSpace);

    // we need to position the deadSpace elements at the front
    for (int i = 0; i < this.deadSpace; i++) {
      this.getArray()[i] = this.getArray()[this.getArray().length - this.deadSpace + i];
    }

    this.setHeapSize(this.deadSpace);
    this.setMaxHeapSize(this.getArray().length);
    this.resetDeadSpace();
    super.buildHeap();
  }

}
