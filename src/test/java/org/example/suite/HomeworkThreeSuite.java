package org.example.suite;

import org.example.PetStoreNegativeTest;
import org.example.PetStorePositiveTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 .
 */
@Suite
@SelectClasses({PetStorePositiveTest.class, PetStoreNegativeTest.class})
public class HomeworkThreeSuite {
}
