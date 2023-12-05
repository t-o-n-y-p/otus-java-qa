package org.example.suite;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 .
 */
@Suite
@SelectClasspathResource("org/example")
@ConfigurationParameter(
    key = Constants.GLUE_PROPERTY_NAME,
    value = "org.example"
)
public class WiremockWebStubSuiteSuite {
}
