package org.example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.restassured.common.mapper.TypeRef;
import java.util.List;
import org.example.clients.JsonUserMockClient;
import org.example.models.UserData;
import org.example.pages.AllUsersPage;

/**
 * .
 */
public class AllUsersPageSteps {

  @Inject
  private AllUsersPage allUsersPage;

  @Inject
  private JsonUserMockClient jsonUserMockClient;

  @Если("Я открываю страницу со списком пользователей")
  public void open() {
    allUsersPage.open();
  }

  @Тогда("Список пользователей актуален")
  public void lessonShouldBeOpened() {
    List<UserData> expectedUserData =
        jsonUserMockClient.getAllUsers().extract().as(new TypeRef<>() {
        });
    allUsersPage.compareUserDataTo(expectedUserData);
  }

}
