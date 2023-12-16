package org.example.components;

import static com.codeborne.selenide.Selenide.$$x;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

public class AbsComponentCollection<T extends AbsComponent> {

  private final Constructor<T> elementConstructor;
  private final String elementXpath;
  private final String collectionXpath;

  private AbsComponentCollection(Constructor<T> elementConstructor, String elementXpath) {
    this.elementConstructor = elementConstructor;
    this.elementXpath = elementXpath;
    collectionXpath = elementXpath.replace("[%d]", "");

  }

  @SneakyThrows
  public T get(int index) {
    return elementConstructor.newInstance(elementXpath.formatted(index + 1));
  }

  public int size() {
    return $$x(collectionXpath).size();
  }

  public List<T> range(int startInclusive, int endExclusive) {
    return IntStream.range(startInclusive, endExclusive).mapToObj(this::get).toList();
  }

  @RequiredArgsConstructor
  public static class Builder<T extends AbsComponent> {

    private final Class<T> elementClass;
    private String elementXpath;

    public Builder<T> xpath(String xpath) {
      elementXpath = xpath;
      return this;
    }

    @SneakyThrows
    public AbsComponentCollection<T> build() {
      return new AbsComponentCollection<>(
          elementClass.getDeclaredConstructor(String.class),
          elementXpath);
    }

  }

}
