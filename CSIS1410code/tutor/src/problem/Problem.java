package problem;

import java.io.Serializable;

/**
 * Abstract Problem class.
 */
public abstract class Problem implements Serializable
{
    /**
     * Supported problem types
     */
    public enum problemTypes
    {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
    }

    public int firstNum;
    public int secondNum;
    public int solution;
    public int answer;

    /**
     * Abstract isSolved method.
     * @return true if the problem is solved.
     */
    public abstract boolean isSolved();

    /**
     * Abstract setAnswer method.
     * @param answer the potential solution to the problem.
     */
    public abstract void setAnswer(int answer);
}
