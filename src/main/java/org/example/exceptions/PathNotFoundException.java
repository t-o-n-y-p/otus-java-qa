package org.example.exceptions;

/**
 * .
 */
public class PathNotFoundException extends RuntimeException {

  public PathNotFoundException(String name) {
    super("Path with name %s not found".formatted(name));
  }

  public PathNotFoundException(Class<?> clazz) {
    super("Path not found for class %s".formatted(clazz.getSimpleName()));
  }

}
