package org.example.extensions;

import com.google.inject.TypeLiteral;
import java.lang.reflect.Field;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

/**
 .
 */
public class AfterEachExtension extends BaseGuiceExtension implements AfterEachCallback {

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    extensionContext.getTestInstance()
        .ifPresent(instance ->
            INJECTOR
                .getAllMembersInjectorInjectionPoints()
                .get(TypeLiteral.get(instance.getClass()))
                .stream()
                .filter(injectionPoint ->
                    injectionPoint.getMember() instanceof Field
                        && WebDriver.class.equals(((Field) injectionPoint.getMember()).getType()))
                .map(injectionPoint -> (Field) injectionPoint.getMember())
                .forEach(field -> quitDriverFromField(instance, field))
        );
  }

  @SneakyThrows
  private void quitDriverFromField(Object testInstance, Field field) {
    ((WebDriver) field.get(testInstance)).quit();
  }
}
