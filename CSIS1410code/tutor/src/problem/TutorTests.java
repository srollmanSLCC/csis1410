package problem;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite runner for our Tutor app.
 */
public class TutorTests
{
    /**
     * Test Suite for our Tutor app.
     * @return A test suite for our Tutor app.
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Tests for Tutor Program");
        // $JUnit-BEGIN$-
        suite.addTestSuite(TutorTest.class);
        // $JUnit-END$
        return suite;
    }
}
