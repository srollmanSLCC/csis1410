package M6A2;

import junit.framework.Test;
import junit.framework.TestSuite;

public class LinkedStackTests
{
    /**
     * Test Suite for LinkedStack<T>
     * @return A test suite for LinkedStack<T>
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for LinkedStack<T>");
        // $JUnit-BEGIN$-
        suite.addTestSuite(LinkedStackTest.class);
        // $JUnit-END$
        return suite;
    }
}
