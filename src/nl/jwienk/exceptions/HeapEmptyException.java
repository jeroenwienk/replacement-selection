package nl.jwienk.exceptions;

public class HeapEmptyException extends Exception {
  public HeapEmptyException() { super(); }
  public HeapEmptyException(String message) { super(message); }
  public HeapEmptyException(String message, Throwable cause) { super(message, cause); }
  public HeapEmptyException(Throwable cause) { super(cause); }
}