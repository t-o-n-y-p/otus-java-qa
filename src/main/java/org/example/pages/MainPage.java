package org.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$x;
import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.selenide.SelenideElement;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.Consumer;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;
import org.example.components.AbsComponentCollection;
import org.example.components.ChatMessage;
import org.example.components.MainMenu;

public class MainPage extends AbsBasePage {

  @Getter
  private final MainMenu mainMenu =
      new MainMenu(
          "//android.widget.FrameLayout[@resource-id='android:id/content']"
              + "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup"
              + "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
              + "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
              + "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
              + "/android.view.ViewGroup/android.view.ViewGroup[1]");

  private final AbsComponentCollection<ChatMessage> chatMessages =
      new AbsComponentCollection.Builder<>(ChatMessage.class)
          .xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]")
          .build();

  private final SelenideElement messageInput = $x("//android.widget.EditText");
  private final SelenideElement sendButton = $x("//android.widget.EditText//following-sibling::android.view.ViewGroup");

  public MainPage skipAlert() {
    if (alert.is(visible)) {
      alert.getOkButton().click();
    }
    return this;
  }

  public MainPage checkHelloMessage() {
    assertThat(chatMessages.size())
        .as("Hello message not found or multiple messages found")
        .isEqualTo(1);
    assertThat(chatMessages.get(0).getText())
        .as("Hello message text is incorrect")
        .isEqualTo("Hello\uD83D\uDE03");
    return this;
  }

  public MainPage sendMessage(String text) {
    messageInput
        .shouldBe(visible.because("Message input field not found on the main page"))
        .sendKeys(text);
    sendButton
        .shouldBe(visible.because("Send button not found on the main page"))
        .click();
    return this;
  }

  @SuppressWarnings("unchecked")
  public MainPage waitForReplies(List<String> replies) {
    waiter.until(
        () -> replies.get(replies.size() - 1).equals(getLastChatMessage().getText()),
        "Andy didn't reply with all expected messages");

    SoftAssertions.assertSoftly(softly -> {
      Consumer<ChatMessage>[] replyChecks =
          Lists.reverse(replies)
              .stream()
              .map(expected ->
                  (Consumer<ChatMessage>) actual ->
                      softly.assertThat(actual.getText())
                          .as("Incorrect reply text from Andy observed")
                          .isEqualTo(expected))
              .toArray(Consumer[]::new);
      softly.assertThat(chatMessages.range(0, replies.size()))
          .as("Incorrect replies from Andy observed")
          .satisfiesExactly(replyChecks);
    });
    return this;
  }

  public MainPage waitForExerciseMessage() {
    waiter.until(
        () -> getLastChatMessage().isExerciseMessage(),
        "Exercise message not found on the main page");
    return this;
  }

  public MainPage waitForExerciseMessageWithReply(String reply) {
    waiter.until(
        () -> getLastChatMessage().isExerciseMessage()
            && reply.equals(chatMessages.get(1).getText()),
        "Exercise message with reply '%s' not found on the main page".formatted(reply));
    return this;
  }

  public MainPage waitForMuchManyLessonMessage() {
    waiter.until(
        () -> getLastChatMessage().isMuchManyLessonMessage(),
        "Much-Many lesson message not found on the main page");
    return this;
  }

  public MainPage waitForMuchManyLessonMessageWithReply() {
    waiter.until(
        () -> getLastChatMessage().isMuchManyLessonMessage()
            && chatMessages.get(1).isPlainMessage(),
        "Much-Many lesson message with reply not found on the main page");
    return this;
  }

  public ChatMessage getLastChatMessage() {
    return chatMessages.get(0);
  }
}
