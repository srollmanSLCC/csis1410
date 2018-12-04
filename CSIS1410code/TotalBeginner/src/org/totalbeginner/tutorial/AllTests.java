package org.totalbeginner.tutorial;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for org.totalbeginner.tutorial");
        // $JUnit-BEGIN$-
        suite.addTestSuite(BookTest.class);
        suite.addTestSuite(PersonTest.class);
        suite.addTestSuite(MyLibraryTest.class);
        // $JUnit-END$
        return suite;
    }
}
