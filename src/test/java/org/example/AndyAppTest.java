package org.example;

import com.google.inject.Inject;
import java.util.List;

import io.qameta.allure.Epic;
import org.example.components.ChatMessage;
import org.example.components.MainMenu;
import org.example.extensions.AndroidExtension;
import org.example.pages.ExercisePage;
import org.example.pages.GrammarPage;
import org.example.pages.MainPage;
import org.example.pages.OnboardingPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings("unused")
@ExtendWith(AndroidExtension.class)
@Epic("Тесты приложения Andy")
public class AndyAppTest {

  @Inject
  private OnboardingPage onboardingPage;
  @Inject
  private MainPage mainPage;
  @Inject
  private ExercisePage exercisePage;
  @Inject
  private GrammarPage grammarPage;

  @Test
  @DisplayName("Приветствие от бота")
  void testGreetings() {
    onboardingPage.skipOnboardingItems();
    mainPage
        .skipAlert()
        .checkHelloMessage()
        .sendMessage("Hello")
        .waitForReplies(List.of("My name is Andy. What is your name?"))
        .sendMessage("Tony")
        .waitForReplies(List.of("Nice to meet you, Tony", "How are you?"));
  }

  @Test
  @DisplayName("Выполнение упражнения")
  void testExercise() {
    onboardingPage.skipOnboardingItems();
    mainPage
        .skipAlert()
        .checkHelloMessage()
        .getMainMenu()
        .clickItem(MainMenu.Item.EXERCISE);
    exercisePage.clickStart();
    mainPage
        .waitForExerciseMessage()
        .getLastChatMessage()
        .clickActionButton(ChatMessage.ActionButton.GOT_IT);
    mainPage
        .waitForExerciseMessageWithReply("Great!");
  }

  @Test
  @DisplayName("Прохождение урока Much - Many")
  void testMuchManyLesson() {
    onboardingPage.skipOnboardingItems();
    mainPage
        .skipAlert()
        .checkHelloMessage()
        .getMainMenu()
        .clickItem(MainMenu.Item.GRAMMAR);
    grammarPage.selectLesson(GrammarPage.Lesson.MUCH_MANY);
    mainPage
        .waitForReplies(
            List.of(
                "Sure",
                "OK. The rule is very simple: "
                    + "you should use \"many\" with countable nouns: \"many cats\".",
                "And \"much\" with uncountable nouns: \"much water\". "
                    + "You can't count water, but can count cats: "
                    + "1 cat, 2 cats, 3 cats, etc. Do you understand?"))
        .sendMessage("yes")
        .waitForMuchManyLessonMessage()
        .getLastChatMessage()
        .clickActionButton(ChatMessage.ActionButton.MANY);
    mainPage
        .waitForMuchManyLessonMessageWithReply()
        .getLastChatMessage()
        .clickActionButton(ChatMessage.ActionButton.MUCH);
    mainPage
        .waitForMuchManyLessonMessageWithReply();
  }
}
