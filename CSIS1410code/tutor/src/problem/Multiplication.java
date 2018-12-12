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
        this.firstNum = r.nextInt(15);
        this.secondNum = r.nextInt(9);
        // We want the larger number on top always.
        if (this.firstNum < this.secondNum)
        {
            int tmp = this.firstNum;
            this.firstNum = this.secondNum;
            this.secondNum = tmp;
        }
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
