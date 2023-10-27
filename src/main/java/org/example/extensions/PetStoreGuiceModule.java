package org.example.extensions;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.petstore.Pet;
import org.example.petstore.User;
import org.example.utils.PetStorePath;

/**
 .
 */
public class PetStoreGuiceModule extends AbstractModule {

  private final User user = new User();
  private final Pet pet = new Pet();

  @Provides
  public User getUser() {
    return user;
  }

  @Provides
  public Pet getPet() {
    return pet;
  }

  /**
   .
   */
  @Provides
  @Singleton
  public RequestSpecification getRequestSpecification() {
    return given()
        .baseUri(PetStorePath.BASE_PATH)
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .log()
        .all();
  }

  /**
   .
   */
  @Provides
  @Singleton
  public ResponseSpecification getResponseSpecification() {
    return expect()
        .contentType(ContentType.JSON)
        .logDetail(LogDetail.ALL);
  }

}
