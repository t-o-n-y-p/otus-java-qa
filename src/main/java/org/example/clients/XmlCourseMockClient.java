package org.example.clients;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import org.example.support.GuiceScoped;
import org.wiremock.integrations.testcontainers.WireMockContainer;

public class XmlCourseMockClient extends BaseClient {

  public XmlCourseMockClient(WireMockContainer wireMockContainer) {
    super(wireMockContainer);
  }

  @Inject
  public XmlCourseMockClient(GuiceScoped scoped) {
    super(scoped);
  }

  @Override
  protected String getBasePath() {
    return "/soap";
  }

  @Override
  protected ContentType getContentType() {
    return ContentType.XML;
  }

  public ValidatableResponse getAllCourses() {
    return given(getDefaultRequestSpecification())
        .when()
        .headers(Map.of(SOAP_ACTION, "/CourseGetAll"))
        .post()
        .then()
        .spec(getDefaultResponseSpecification())
        .body(matchesXsdInClasspath("schema/courses.xsd"));
  }
}
