package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import lombok.Getter;
import org.example.containers.WireMockContainer;
import org.testcontainers.utility.MountableFile;

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
  public WireMockContainer getContainer() {
    return wiremockContainer;
  }
}
