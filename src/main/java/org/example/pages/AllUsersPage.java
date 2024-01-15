package org.example.pages;

import com.google.inject.Inject;
import java.util.List;
import java.util.function.Consumer;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.example.annotations.Path;
import org.example.components.UserData;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;

@Path("user/get/all")
@SuppressWarnings("unchecked")
public class AllUsersPage extends AbsBasePage<AllUsersPage> {

  @Inject
  public AllUsersPage(GuiceScoped scoped) {
    super(scoped);
  }

  public List<UserData> getUserData() {
    return driver.findElements(By.tagName("td"))
        .stream()
        .map(e -> new UserData(scoped, e))
        .toList();
  }

  @Step("Сравнить данные пользователей на странице с данными из API")
  public void compareUserDataTo(List<org.example.models.UserData> expectedUserData) {
    SoftAssertions softly = new SoftAssertions();
    Consumer<UserData>[] checks = expectedUserData.stream()
        .map(expected -> (Consumer<UserData>) actual -> {
          softly.assertThat(actual.getName())
              .as("User name is incorrect")
              .isEqualTo(expected.getName());
          softly.assertThat(actual.getCourse())
              .as("User course name is incorrect")
              .isEqualTo(expected.getCourse());
          softly.assertThat(actual.getEmail())
              .as("User email is incorrect")
              .isEqualTo(expected.getEmail());
          softly.assertThat(actual.getAge())
              .as("User age is incorrect")
              .isEqualTo(expected.getAge());
        }).toArray(Consumer[]::new);

    softly.assertThat(getUserData())
        .as("User data from page and API don't match")
        .satisfiesExactly(checks);

    softly.assertAll();

  }
}
