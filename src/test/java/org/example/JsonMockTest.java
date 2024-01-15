package org.example;

import static org.hamcrest.Matchers.greaterThan;

import com.google.inject.Inject;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.example.clients.JsonCourseMockClient;
import org.example.clients.JsonUserMockClient;
import org.example.extensions.WiremockExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith(WiremockExtension.class)
@Epic("Тесты API заглушек Wiremock")
@Story("JSON")
public class JsonMockTest {

  @Inject
  private JsonUserMockClient jsonUserMockClient;

  @Inject
  private JsonCourseMockClient jsonCourseMockClient;

  @Test
  @DisplayName("Проверка заглушки для списка пользователей")
  void testAllUsers() {
    jsonUserMockClient
        .getAllUsers()
        .body("$.size()", greaterThan(0));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 23, 456})
  @DisplayName("Проверка заглушки для оценки пользователя по ID")
  void testUserScore(int id) {
    jsonUserMockClient.getUserScore(id);
  }

  @Test
  @DisplayName("Проверка заглушки для списка курсов")
  void testAllCourses() {
    jsonCourseMockClient
        .getAllCourses()
        .body("$.size()", greaterThan(0));
  }
}
