package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.clients.JsonCourseMockClient;
import org.example.clients.JsonUserMockClient;
import org.example.clients.XmlCourseMockClient;
import org.example.clients.XmlUserMockClient;
import org.testcontainers.utility.MountableFile;
import org.wiremock.integrations.testcontainers.WireMockContainer;

/**
 * .
 */
public class WiremockModule extends AbstractModule {

  private static final WireMockContainer CONTAINER =
      new WireMockContainer(WireMockContainer.WIREMOCK_2_LATEST)
          .withCopyFileToContainer(
              MountableFile.forClasspathResource("/wiremock"),
              "/home/wiremock");

  public static WireMockContainer getWiremockContainer() {
    return CONTAINER;
  }

  @Provides
  @Singleton
  public JsonCourseMockClient jsonCourseMockClient() {
    return new JsonCourseMockClient(CONTAINER);
  }

  @Provides
  @Singleton
  public JsonUserMockClient jsonUserMockClient() {
    return new JsonUserMockClient(CONTAINER);
  }

  @Provides
  @Singleton
  public XmlCourseMockClient xmlCourseMockClient() {
    return new XmlCourseMockClient(CONTAINER);
  }

  @Provides
  @Singleton
  public XmlUserMockClient xmlUserMockClient() {
    return new XmlUserMockClient(CONTAINER);
  }

}
