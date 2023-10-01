package org.example.extensions;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 .
 */
public class BeforeEachExtension extends BaseGuiceExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    extensionContext.getTestInstance()
        .ifPresent(INJECTOR::injectMembers);
  }
}
