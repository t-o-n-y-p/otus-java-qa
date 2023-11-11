package org.example.extensions.modules;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

import com.google.inject.AbstractModule;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseModule extends AbstractModule {

  protected RequestSpecification getRequestSpecification() {
    return given()
        .baseUri(System.getProperty("base.url"))
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .log()
        .all();
  }

  protected ResponseSpecification getResponseSpecification() {
    return expect()
        .contentType(ContentType.JSON)
        .logDetail(LogDetail.ALL);
  }

}
