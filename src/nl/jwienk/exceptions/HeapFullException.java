package nl.jwienk.exceptions;

public class HeapFullException extends Exception {
  public HeapFullException() { super(); }
  public HeapFullException(String message) { super(message); }
  public HeapFullException(String message, Throwable cause) { super(message, cause); }
  public HeapFullException(Throwable cause) { super(cause); }
}