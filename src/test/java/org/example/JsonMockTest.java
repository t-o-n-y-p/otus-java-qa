package org.example;

import static org.hamcrest.Matchers.greaterThan;

import com.google.inject.Inject;
import org.example.clients.JsonCourseMockClient;
import org.example.clients.JsonUserMockClient;
import org.example.extensions.WiremockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith(WiremockExtension.class)
public class JsonMockTest {

  @Inject
  private JsonUserMockClient jsonUserMockClient;

  @Inject
  private JsonCourseMockClient jsonCourseMockClient;

  @Test
  void testAllUsers() {
    jsonUserMockClient
        .getAllUsers()
        .body("$.size()", greaterThan(0));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 23, 456})
  void testUserScore(int id) {
    jsonUserMockClient.getUserScore(id);
  }

  @Test
  void testAllCourses() {
    jsonCourseMockClient
        .getAllCourses()
        .body("$.size()", greaterThan(0));
  }
}
