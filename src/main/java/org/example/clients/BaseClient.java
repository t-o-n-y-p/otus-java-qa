package org.example.clients;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import com.google.inject.Inject;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.example.support.GuiceScoped;
import org.hamcrest.Matchers;
import org.wiremock.integrations.testcontainers.WireMockContainer;
import java.util.Arrays;
import java.util.stream.Stream;

public abstract class BaseClient {

  public static final String SOAP_ACTION = "SOAPAction";

  private final String baseUrl;

  public BaseClient(WireMockContainer wireMockContainer) {
    baseUrl = wireMockContainer.getBaseUrl();
  }

  @Inject
  public BaseClient(GuiceScoped scoped) {
    baseUrl = "http://localhost:50000";
  }

  protected abstract String getBasePath();

  protected abstract ContentType getContentType();

  protected RequestSpecification getDefaultRequestSpecification() {
    return given()
        .baseUri(baseUrl)
        .basePath(getBasePath())
        .contentType(getContentType())
        .accept(getContentType())
        .log()
        .all();
  }

  protected ResponseSpecification getDefaultResponseSpecification() {
    return expect()
        .statusCode(equalTo(HttpStatus.SC_OK))
        .contentType(getContentType())
        .logDetail(LogDetail.ALL);
  }

}
