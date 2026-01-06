package org.example.pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.example.annotations.UrlTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings("unused")
@Singleton
@UrlTemplate("/")
public class MainPage extends AbsBasePage<MainPage> {

  @FindBy(css = "a.photo__title")
  private List<WebElement> articles;

  @Inject
  public MainPage(WebDriver driver) {
    super(driver);
  }

  public String getPhotoTitleByIndex(int index) {
    assertThat(articles)
        .as("Индекс больше количества элементов списка")
        .hasSizeGreaterThanOrEqualTo(index);
    String result = articles.get(index - 1).getText();
    assertThat(result).as("Заголовок фото %d пуст", index).isNotBlank();
    return result;
  }

  public void clickArticleByTitle(String title) {
    clickFirstElementByPredicate(articles, e -> title.equals(e.getText()));
  }
}
