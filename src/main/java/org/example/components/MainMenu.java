package org.example.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MainMenu extends AbsComponent {

  public MainMenu(String xpath) {
    super(xpath);
  }

  public void clickItem(Item item) {
    $x(xpath + "//android.widget.TextView[@text='%s']".formatted(item.name))
        .shouldBe(visible.because("%s button not found in the main menu".formatted(item.name)))
        .click();
  }

  @AllArgsConstructor
  @Getter
  public enum Item {
    CHAT("Chat"),
    EXERCISE("Exercise"),
    GRAMMAR("Grammar"),
    STATS("Stats");

    private final String name;
  }
}
