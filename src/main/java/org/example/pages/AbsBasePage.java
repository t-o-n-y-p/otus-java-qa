package org.example.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
        AnnotationSupport.findRepeatableAnnotations(getClass(), UrlTemplate.class);
    for (UrlTemplate template : templates) {
      if (name.equals(template.name())) {
        return template.value().formatted((Object[]) params);
      }
    }
    throw new PathNotFoundException(name);
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
    driver.get(baseUrl + getPath(name, params));
    return (T) this;
  }

  public T open() {
    driver.get(baseUrl + getPath());
    return (T) this;
  }

  public T pageHeaderShouldBeSameAs(String value) {
    assertThat(header).as("Обнаружен пустой заголовок").isNotNull();
    assertThat(header.getText()).as("Неожидаемый текст заголовка").isEqualTo(value);
    return (T) this;
  }
}
