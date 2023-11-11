package org.example.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import io.restassured.specification.RequestSpecification;
import org.example.annotations.PetById;
import org.example.annotations.UserByUsername;
import org.example.extensions.modules.PetModule;
import org.example.extensions.modules.UserModule;
import org.example.petstore.Pet;
import org.example.petstore.User;
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
          injector = Guice.createInjector(new UserModule(), new PetModule());
          injector.injectMembers(instance);
        });
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    String username = injector.getInstance(User.class).getUsername();
    if (username != null) {
      injector
          .getInstance(Key.get(RequestSpecification.class, UserByUsername.class))
          .pathParam("username", username)
          .delete();
    }
    Long id = injector.getInstance(Pet.class).getId();
    if (id != null) {
      injector
          .getInstance(Key.get(RequestSpecification.class, PetById.class))
          .pathParam("petId", id)
          .delete();
    }
  }
}
