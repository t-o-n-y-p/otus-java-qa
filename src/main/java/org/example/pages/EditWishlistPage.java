package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class EditWishlistPage extends AbsBasePage {

  private final SelenideElement title =
      $(id("ru.otus.wishlist:id/wishlist_edit_title"))
          .as("Заголовок формы редактирования списка желаний");
  private final SelenideElement wishlistTitleInputField =
      $(id("ru.otus.wishlist:id/title_input"))
          .as("Поле ввода заголовка списка желаний");
  private final SelenideElement wishlistDescriptionInputField =
      $(id("ru.otus.wishlist:id/description_input"))
          .as("Поле ввода подзаголовка списка желаний");
  private final SelenideElement saveButton =
      $(id("ru.otus.wishlist:id/save_button"))
          .as("Кнопка сохранения списка желаний");

  public EditWishlistPage assertEditWishlistTitle(String expected) {
    title
        .shouldBe(visible.because("Заголовок формы редактирования списка желаний не виден на экране"))
        .shouldHave(text(expected).because("Неверный текст заголовка формы редактирования списка желаний"));
    return this;
  }

  public void edit(String title, String description) {
    wishlistTitleInputField
        .shouldBe(visible.because("Поле ввода заголовка списка желаний не видно на экране"))
        .clear();
    wishlistTitleInputField.sendKeys(title);
    wishlistDescriptionInputField
        .shouldBe(visible.because("Поле ввода подзаголовка списка желаний не видно на экране"))
        .clear();
    wishlistDescriptionInputField.sendKeys(description);
    saveButton
        .shouldBe(visible.because("Кнопка сохранения не видна на экране"))
        .click();
  }
}
