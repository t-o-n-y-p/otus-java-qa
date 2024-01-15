package org.example.clients;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public abstract class BaseClient {

  protected abstract String getBasePath();

  protected RequestSpecification getDefaultRequestSpecification() {
    return given()
        .baseUri(System.getProperty("base.url"))
        .basePath(getBasePath())
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .filter(new AllureRestAssured())
        .log()
        .all();
  }

  protected ResponseSpecification getDefaultResponseSpecification() {
    return expect()
        .statusCode(equalTo(HttpStatus.SC_OK))
        .contentType(ContentType.JSON)
        .logDetail(LogDetail.ALL);
  }

}
