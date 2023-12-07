package org.example.steps.pages;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.mapping.Jackson2Mapper;
import java.util.List;
import org.example.clients.JsonUserMockClient;
import org.example.clients.XmlUserMockClient;
import org.example.models.Envelope;
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

  @Inject
  private XmlUserMockClient xmlUserMockClient;

  @Если("Я открываю страницу со списком пользователей")
  public void open() {
    allUsersPage.open();
  }

  @Тогда("Список пользователей актуален JSON")
  public void checkUsersJson() {
    List<UserData> expectedUserData =
        jsonUserMockClient.getAllUsers().extract().as(new TypeRef<>() {
        });
    allUsersPage.compareUserDataTo(expectedUserData);
  }

  @Тогда("Список пользователей актуален XML")
  public void checkUsersXml() {
    List<UserData> expectedUserData =
        xmlUserMockClient.getAllUsers().extract()
            .as(Envelope.class, new Jackson2Mapper((t, s) -> new XmlMapper()))
            .getBody()
            .getUserGetAllResponse()
            .getUserData();
    allUsersPage.compareUserDataTo(expectedUserData);
  }

}
