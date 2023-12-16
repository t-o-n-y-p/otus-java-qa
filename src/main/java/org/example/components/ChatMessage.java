package org.example.components;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ChatMessage extends AbsComponent {

  private final ElementsCollection messageTextParts =
      $$x(
          xpath
              + "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
              + "/android.view.ViewGroup[@clickable='false']//android.widget.TextView");

  public ChatMessage(String xpath) {
    super(xpath);
  }

  private SelenideElement getActionButtonElement(ActionButton actionButton) {
    return $x(
        xpath
            + "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
            + "/android.view.ViewGroup["
            + "@clickable='true' and .//android.widget.TextView[@text='%s']"
            .formatted(actionButton.name)
            + "]");
  }

  public String getText() {
    return messageTextParts
        .asDynamicIterable()
        .stream()
        .map(SelenideElement::getText)
        .collect(Collectors.joining(" "))
        .trim();
  }

  public boolean isTextNotBlank() {
    return !messageTextParts
        .filter(matchText("\\S+"))
        .isEmpty();
  }

  public boolean isPlainMessage() {
    return isTextNotBlank()
        && $$x(
        xpath
            + "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
            + "/android.view.ViewGroup[@clickable='true']")
        .size() == 1;
  }

  public boolean isExerciseMessage() {
    return isTextNotBlank()
        && Stream.of(ActionButton.GOT_IT, ActionButton.I_KNOW_THIS_WORD, ActionButton.EXPLAIN)
        .allMatch(actionButton -> getActionButtonElement(actionButton).is(visible));
  }

  public boolean isMuchManyLessonMessage() {
    return isTextNotBlank()
        && Stream.of(ActionButton.MUCH, ActionButton.MANY)
        .allMatch(actionButton -> getActionButtonElement(actionButton).is(visible));
  }

  public void clickActionButton(ActionButton actionButton) {
    getActionButtonElement(actionButton)
        .shouldBe(visible.because("'%s' button not found in the chat message".formatted(actionButton.name)))
        .click();
  }

  @AllArgsConstructor
  @Getter
  public enum ActionButton {
    GOT_IT("✅ Got it"),
    I_KNOW_THIS_WORD("\uD83D\uDE0E I know this word"),
    EXPLAIN("\uD83E\uDD14 Explain"),
    MUCH("much"),
    MANY("many");

    private final String name;
  }
}
