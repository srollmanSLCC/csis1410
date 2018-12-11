package problem;

import junit.framework.TestCase;
import java.util.ArrayList;

/**
 * Class for testing aspects of our Tutor app.
 */
public class TutorTest extends TestCase
{
    /**
     * Test method for Student
     */
    public void testStudent()
    {
        Student s = new Student();
        assertNull(s.getName());
        s.setName("Test");
        assertEquals("Test", s.getName());
        assertFalse(s.hasProblems());
        s.addProblem(Problem.problemTypes.ADDITION);
        assertTrue(s.hasProblems());
        assertEquals(0, s.getMostRecentProblemIndex());
        ArrayList<Problem> problems = s.getProblems();
        for (int i = 0; i < problems.size(); i++)
        {
            Addition add = (Addition) problems.get(i);
            assertFalse(s.trySolveProblem(i, add.solution + 1));
            assertTrue(s.trySolveProblem(i, add.solution));
        }
    }

    /**
     * Test method for Student with name
     */
    public void testStudentWithName()
    {
        Student s = new Student("Test");
        assertEquals("Test", s.getName());
        assertFalse(s.hasProblems());
        s.addProblem(Problem.problemTypes.ADDITION);
        assertTrue(s.hasProblems());
        assertEquals(0, s.getMostRecentProblemIndex());
        ArrayList<Problem> problems = s.getProblems();
        for (int i = 0; i < problems.size(); i++)
        {
            Addition add = (Addition) problems.get(i);
            assertFalse(s.trySolveProblem(i, add.solution + 1));
            assertTrue(s.trySolveProblem(i, add.solution));
        }
    }

    /**
     * Test method for Student with name and Problems
     */
    public void testStudentWithNameAndProblems()
    {
        ArrayList<Problem> problems = new ArrayList<>();
        problems.add(new Addition());
        Student s = new Student("Test", problems);
        assertEquals("Test", s.getName());
        assertTrue(s.hasProblems());
        assertEquals(0, s.getMostRecentProblemIndex());
        problems = s.getProblems();
        for (int i = 0; i < problems.size(); i++)
        {
            Addition add = (Addition) problems.get(i);
            assertFalse(s.trySolveProblem(i, add.solution + 1));
            assertTrue(s.trySolveProblem(i, add.solution));
        }
    }

    /**
     * Test for Addition Class
     */
    public void testAddition()
    {
        Addition addProb = new Addition();
        assertFalse(addProb.isSolved());
        assertEquals(addProb.solution, addProb.firstNum + addProb.secondNum);
        addProb.setAnswer(addProb.firstNum + addProb.secondNum);
        assertTrue(addProb.isSolved());
    }

    /**
     * Test for Subtraction Class
     */
    public void testSubtraction()
    {
        Subtraction subProb = new Subtraction();
        assertFalse(subProb.isSolved());
        assertEquals(subProb.solution, subProb.firstNum - subProb.secondNum);
        subProb.setAnswer(subProb.firstNum - subProb.secondNum);
        assertTrue(subProb.isSolved());
    }

    /**
     * Test for Multiplication Class.
     */
    public void testMultiplication()
    {
        Multiplication multProb = new Multiplication();
        assertFalse(multProb.isSolved());
        assertEquals(multProb.solution, multProb.firstNum * multProb.secondNum);
        multProb.setAnswer(multProb.firstNum * multProb.secondNum);
        assertTrue(multProb.isSolved());
    }

    /**
     * Test for Division Class.
     */
    public void testDivision()
    {
        Division divProb = new Division();
        assertFalse(divProb.isSolved());
        assertEquals(divProb.solution, divProb.firstNum / divProb.secondNum);
        divProb.setAnswer(divProb.firstNum / divProb.secondNum);
        assertTrue(divProb.isSolved());
    }

}
