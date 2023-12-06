package org.example.extensions;

import com.google.inject.Guice;
import io.cucumber.guice.ScenarioScoped;
import org.example.factory.WiremockModule;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WiremockExtension implements BeforeAllCallback, BeforeEachCallback, ExtensionContext.Store.CloseableResource {

    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean started = new AtomicBoolean(false);

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        lock.lock();
        try {
            if (!started.get()) {
                WiremockModule.getWiremockContainer().start();
                started.set(true);
                extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put("wiremock", this);
            }
        } finally {
            lock.unlock();
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
