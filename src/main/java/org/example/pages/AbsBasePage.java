package org.example.pages;

import java.util.Arrays;
import java.util.Optional;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.StringUtils;
import org.example.annotations.Path;
import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.example.exceptions.PathNotFoundException;
import org.example.pageobject.AbsPageObject;
import org.openqa.selenium.WebDriver;

/**
 .
 */
public abstract class AbsBasePage<T extends AbsBasePage<T>> extends AbsPageObject {

  public static final String BASE_URL = System.getProperty("base.url");

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String getPath(String name, String... params) {
    Class<?> clazz = getClass();
    if (!clazz.isAnnotationPresent(UrlTemplate.class)) {
      throw new PathNotFoundException(clazz);
    }
    Optional<Template> template = Arrays.stream(clazz.getAnnotation(UrlTemplate.class).value())
        .filter(t -> name.equals(t.name()))
        .findFirst();
    if (template.isEmpty()) {
      throw new PathNotFoundException(name);
    }
    return template.get().template().formatted((Object[]) params);
  }

  private String getPath() {
    Class<?> clazz = getClass();
    if (!clazz.isAnnotationPresent(Path.class)) {
      throw new PathNotFoundException(clazz);
    }
    return clazz.getAnnotation(Path.class).value();
  }

  @SuppressWarnings("unchecked")
  public T open(String name, String... params) {
    String url = StringUtils.appendIfMissing(BASE_URL, "/") + getPath(name, params);
    Allure.step("Открыть страницу " + url);
    driver.get(url);
    return (T) this;
  }

  /**
   .
   */
  @SuppressWarnings("unchecked")
  public T open() {
    String url = StringUtils.appendIfMissing(BASE_URL, "/") + getPath();
    Allure.step("Открыть страницу " + url);
    driver.get(url);
    return (T) this;
  }
}
