package org.example;

import static org.hamcrest.Matchers.greaterThan;

import com.google.inject.Inject;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.example.clients.XmlCourseMockClient;
import org.example.clients.XmlUserMockClient;
import org.example.extensions.WiremockExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith(WiremockExtension.class)
@Epic("Тесты API заглушек Wiremock")
@Story("XML")
public class XmlMockTest {

  @Inject
  private XmlUserMockClient xmlUserMockClient;

  @Inject
  private XmlCourseMockClient xmlCourseMockClient;

  @Test
  @DisplayName("Проверка заглушки для списка пользователей")
  void testAllUsers() {
    xmlUserMockClient
        .getAllUsers()
        .body("Envelope.Body.UserGetAllResponse.Users.children().size()", greaterThan(0));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 23, 456})
  @DisplayName("Проверка заглушки для оценки пользователя по ID")
  void testUserScore(int id) {
    xmlUserMockClient.getUserScore(id);
  }

  @Test
  @DisplayName("Проверка заглушки для списка курсов")
  void testAllCourses() {
    xmlCourseMockClient
        .getAllCourses()
        .body("Envelope.Body.CourseGetAllResponse.Courses.children().size()", greaterThan(0));
  }
}
