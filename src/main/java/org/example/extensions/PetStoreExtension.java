package org.example.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.restassured.specification.RequestSpecification;
import org.example.petstore.User;
import org.example.utils.PetStorePath;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 .
 */
public class PetStoreExtension implements BeforeEachCallback, AfterEachCallback {

  private Injector injector;

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    extensionContext.getTestInstance()
        .ifPresent(instance -> {
          injector = Guice.createInjector(new PetStoreGuiceModule());
          injector.injectMembers(instance);
        });
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    String username = injector.getInstance(User.class).getUsername();
    if (username != null) {
      injector
          .getInstance(RequestSpecification.class)
          .pathParam("username", username)
          .delete(PetStorePath.USER_BY_USERNAME);
    }
  }
}
