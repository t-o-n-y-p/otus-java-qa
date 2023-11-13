package org.example.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.clients.PetClient;
import org.example.clients.UserClient;
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
      injector.getInstance(UserClient.class).deleteUser(username);
    }
    Long id = injector.getInstance(Pet.class).getId();
    if (id != null) {
      injector.getInstance(PetClient.class).deletePet(id);
    }
  }
}
