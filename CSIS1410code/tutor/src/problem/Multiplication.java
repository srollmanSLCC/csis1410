package problem;

import java.util.Random;

/**
 * Multiplication class.
 */
public class Multiplication extends Problem
{
    /**
     * Default constructor.
     */
    public Multiplication ()
    {
        Random r = new Random();
        this.firstNum = r.nextInt(20);
        this.secondNum = r.nextInt(20);
        this.solution = firstNum * secondNum;
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
        return String.format("%d X %d = %s", firstNum, secondNum, (isSolved()) ? "" + solution : "?");
    }
}
