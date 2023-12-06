package org.example.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.example.factory.WiremockModule;
import org.example.support.GuiceScoped;
import org.wiremock.integrations.testcontainers.WireMockContainer;

/**
 * .
 */
public class Hooks {

  private static final Lock LOCK = new ReentrantLock();
  private static final AtomicBoolean STARTED = new AtomicBoolean(false);
  @Inject
  private GuiceScoped scoped;

  @BeforeAll
  public static void before_all() {
    LOCK.lock();
    try {
      if (!STARTED.get()) {
        WiremockModule.getWiremockContainer().start();
        STARTED.set(true);
      }
    } finally {
      LOCK.unlock();
    }
  }

  @AfterAll
  public static void after_all() {
    WireMockContainer container = WiremockModule.getWiremockContainer();
    if (container.isRunning()) {
      container.stop();
    }
  }

  /**
   * .
   */
  @After
  public void afterScenario() {
    if (scoped.getDriver() != null) {
      scoped.getDriver().quit();
    }
  }
}
