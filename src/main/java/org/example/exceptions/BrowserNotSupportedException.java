package org.example.exceptions;

/**
 .
 */
public class BrowserNotSupportedException extends RuntimeException {

  public BrowserNotSupportedException(String browserName) {
    super("Browser %s not supported".formatted(browserName));
  }
}
