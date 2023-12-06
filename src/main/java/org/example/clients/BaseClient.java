package org.example.clients;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.example.support.GuiceScoped;
import org.wiremock.integrations.testcontainers.WireMockContainer;

public abstract class BaseClient {

  public static final String SOAP_ACTION = "SOAPAction";

  private final WireMockContainer wireMockContainer;

  public BaseClient(WireMockContainer wireMockContainer) {
    this.wireMockContainer = wireMockContainer;
  }

  @Inject
  public BaseClient(GuiceScoped scoped) {
    wireMockContainer = scoped.getContainer();
  }

  protected abstract String getBasePath();

  protected abstract ContentType getContentType();

  protected RequestSpecification getDefaultRequestSpecification() {
    return given()
        .baseUri(wireMockContainer.getBaseUrl())
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
