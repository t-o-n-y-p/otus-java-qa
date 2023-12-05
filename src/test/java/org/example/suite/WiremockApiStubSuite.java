package org.example.suite;

import org.example.JsonMockTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({JsonMockTest.class})
public class WiremockApiStubSuite {
}
