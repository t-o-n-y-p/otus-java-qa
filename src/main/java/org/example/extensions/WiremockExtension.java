package org.example.extensions;

import com.google.inject.Guice;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.example.factory.WiremockModule;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.wiremock.integrations.testcontainers.WireMockContainer;

public class WiremockExtension implements BeforeAllCallback, BeforeEachCallback, ExtensionContext.Store.CloseableResource {

  private static final Lock LOCK = new ReentrantLock();
  private static final AtomicBoolean STARTED = new AtomicBoolean(false);

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    LOCK.lock();
    try {
      if (!STARTED.get()) {
        WiremockModule.getWiremockContainer().start();
        STARTED.set(true);
        extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put("wiremock", this);
      }
    } finally {
      LOCK.unlock();
    }
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    extensionContext
        .getTestInstance()
        .ifPresent(instance -> Guice.createInjector(new WiremockModule()).injectMembers(instance));
  }

  @Override
  public void close() throws Throwable {
    WireMockContainer container = WiremockModule.getWiremockContainer();
    if (container.isRunning()) {
      container.stop();
    }
  }
}
