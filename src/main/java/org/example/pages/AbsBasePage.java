package org.example.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.apache.commons.lang3.Strings;
import org.example.annotations.UrlTemplate;
import org.example.exceptions.PathNotFoundException;
import org.example.pageobject.AbsPageObject;
import org.junit.platform.commons.support.AnnotationSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings({"unused", "unchecked"})
public abstract class AbsBasePage<T extends AbsBasePage<T>> extends AbsPageObject {

  private final String baseUrl = System.getProperty("base.url");

  @FindBy(tagName = "h1")
  private WebElement header;

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String getPath(String name, String... params) {
    List<UrlTemplate> templates =
        AnnotationSupport.findRepeatableAnnotations(getClass(), UrlTemplate.class).stream()
            .filter(a -> name.equals(a.name()))
            .toList();
    if (templates.size() != 1) {
      throw new PathNotFoundException(name);
    }
    return templates.get(0).value().formatted((Object[]) params);
  }

  private String getPath() {
    Class<?> clazz = getClass();
    List<UrlTemplate> templates =
        AnnotationSupport.findRepeatableAnnotations(clazz, UrlTemplate.class);
    if (templates.size() != 1) {
      throw new PathNotFoundException(clazz);
    }
    return templates.get(0).value();
  }

  public T open(String name, String... params) {
    String url = Strings.CS.appendIfMissing(baseUrl, "/") + getPath(name, params);
    driver.get(url);
    return (T) this;
  }

  public T open() {
    String url = Strings.CS.appendIfMissing(baseUrl, "/") + getPath();
    driver.get(url);
    return (T) this;
  }

  public T pageHeaderShouldBeSameAs(String value) {
    assertThat(header).as("Обнаружен пустой заголовок").isNotNull();
    assertThat(header.getText()).as("Неожидаемый текст заголовка").isEqualTo(value);
    return (T) this;
  }
}
