package problem;

import java.util.Random;

/**
 * Division class
 */
public class Division extends Problem
{
    /**
     * Default constructor.
     */
    public Division ()
    {
        Random r = new Random();
        // ensure we don't get a 0 by adding 1
        this.secondNum = r.nextInt(8) + 1;
        this.firstNum = this.secondNum * (r.nextInt(19) + 1);
        this.solution = firstNum / secondNum;
        this.answer = -10000;
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
        return String.format("%d / %d = %s", firstNum, secondNum, (isSolved()) ? "" + solution : "?");
    }
}
