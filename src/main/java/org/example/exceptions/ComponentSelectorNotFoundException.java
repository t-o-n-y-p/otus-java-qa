package org.example.exceptions;

public class ComponentSelectorNotFoundException extends RuntimeException {

  public ComponentSelectorNotFoundException(Class<?> clazz) {
    super("Component selector not found for class %s".formatted(clazz.getSimpleName()));
  }
}
