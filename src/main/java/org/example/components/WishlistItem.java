package org.example.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

public class WishlistItem extends AbsComponent<WishlistItem> {

  private final SelenideElement title =
      root.$(id("ru.otus.wishlist:id/title")).as("Заголовок списка желаний");
  private final SelenideElement subtitle =
      root.$(id("ru.otus.wishlist:id/subtitle")).as("Подзаголовок списка желаний");
  private final SelenideElement editButton =
      root.$(id("ru.otus.wishlist:id/edit_button")).as("Кнопка редактирования списка желаний");

  public WishlistItem(SelenideElement root) {
    super(root);
  }

  public void assertTitleEqualsTo(String value) {
    title.shouldHave(
        text(value).because("Заголовок списка желаний не совпадает с ожидаемым"));
  }

  public void assertSubtitleEqualsTo(String value) {
    subtitle.shouldHave(
        text(value).because("Подзаголовок списка желаний не совпадает с ожидаемым"));
  }

  public void tapEdit() {
    editButton
        .shouldBe(visible.because("Кнопка редактирования списка желаний не видна на экране"))
        .click();
  }
}
