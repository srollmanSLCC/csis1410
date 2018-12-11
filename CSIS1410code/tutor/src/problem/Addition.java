package problem;

import java.util.Random;

/**
 * Addition class.
 */
public class Addition extends Problem
{
    /**
     * Default constructor.
     */
    public Addition ()
    {
        Random r = new Random();
        this.firstNum = r.nextInt(99);
        this.secondNum = r.nextInt(99);
        this.solution = firstNum + secondNum;
        this.answer = -100000;
    }

    /**
     * Overridden isSolved method from Parent.
     * @return true if this problem is solved.
     */
    @Override
    public boolean isSolved()
    {
        return this.solution == this.answer;
    }

    /**
     * Overridden setAnswer method from parent.
     * @param answer the answer to set as the current answer to our problem (regardless if correct)
     */
    @Override
    public void setAnswer(int answer)
    {
        this.answer = answer;
    }

    /**
     * Overridden toString method.
     * @return A string representing this problem.
     */
    @Override
    public String toString()
    {
        return String.format("%d + %d = %s", firstNum, secondNum, (isSolved()) ? "" + solution : "?");
    }
}
