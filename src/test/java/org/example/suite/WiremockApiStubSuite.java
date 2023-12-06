package org.example.suite;

import org.example.JsonMockTest;
import org.example.XmlMockTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({JsonMockTest.class, XmlMockTest.class})
public class WiremockApiStubSuite {
}
