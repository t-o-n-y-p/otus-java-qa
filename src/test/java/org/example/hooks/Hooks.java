package org.example.hooks;

import com.google.inject.Inject;
import io.cucumber.java.*;
import org.example.factory.WiremockModule;
import org.example.support.GuiceScoped;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 .
 */
public class Hooks {

  @Inject
  private GuiceScoped scoped;

  private static final Lock lock = new ReentrantLock();
  private static final AtomicBoolean started = new AtomicBoolean(false);

  @BeforeAll
  public static void before_all() {
    lock.lock();
    try {
      if (!started.get()) {
        WiremockModule.getWiremockContainer().start();
        started.set(true);
      }
    } finally {
      lock.unlock();
    }
  }

  /**
   .
   */
  @After
  public void afterScenario() {
    if (scoped.getDriver() != null) {
      scoped.getDriver().quit();
    }
  }

  @AfterAll
  public static void after_all() {
    WireMockContainer container = WiremockModule.getWiremockContainer();
    if (container.isRunning()) {
      container.stop();
    }
  }
}
