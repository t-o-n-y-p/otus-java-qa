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
@UrlTemplate(template = "/")
public class MainPage extends AbsBasePage<MainPage> {

  @FindBy(css = "a.photo__title")
  private List<WebElement> articles;

  private final ArticlePage articlePage;

  @Inject
  public MainPage(WebDriver driver, ArticlePage articlePage) {
    super(driver);
    this.articlePage = articlePage;
  }

  public String getPhotoTitleByIndex(int index) {
    assertThat(articles)
        .as("Индекс больше количества элементов списка")
        .hasSizeGreaterThanOrEqualTo(index);
    return articles.get(index - 1).getText();
  }

  public ArticlePage clickArticleByTitle(String title) {
    clickFirstElementByPredicate(articles, e -> title.equals(e.getText()));
    return articlePage;
  }
}
