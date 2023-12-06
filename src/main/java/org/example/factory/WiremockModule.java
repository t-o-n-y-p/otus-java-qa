package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.cucumber.guice.ScenarioScoped;
import lombok.Getter;
import org.example.clients.JsonCourseMockClient;
import org.example.clients.JsonUserMockClient;
import org.example.clients.XmlCourseMockClient;
import org.example.clients.XmlUserMockClient;
import org.testcontainers.utility.MountableFile;
import org.wiremock.integrations.testcontainers.WireMockContainer;

/**
 .
 */
public class WiremockModule extends AbstractModule {

  @Getter
  private static final WireMockContainer wiremockContainer =
          new WireMockContainer(WireMockContainer.WIREMOCK_2_LATEST)
                  .withCopyFileToContainer(
                          MountableFile.forClasspathResource("/wiremock"),
                          "/home/wiremock");

  @Provides
  @Singleton
  public JsonCourseMockClient jsonCourseMockClient() {
    return new JsonCourseMockClient(wiremockContainer);
  }

  @Provides
  @Singleton
  public JsonUserMockClient jsonUserMockClient() {
    return new JsonUserMockClient(wiremockContainer);
  }

  @Provides
  @Singleton
  public XmlCourseMockClient xmlCourseMockClient() {
    return new XmlCourseMockClient(wiremockContainer);
  }

  @Provides
  @Singleton
  public XmlUserMockClient xmlUserMockClient() {
    return new XmlUserMockClient(wiremockContainer);
  }

}
