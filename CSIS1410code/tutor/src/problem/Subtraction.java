package problem;

import java.util.Random;

/**
 * Subtraction Class
 */
public class Subtraction extends Problem
{
    /**
     * No args constructor.
     */
    public Subtraction ()
    {
        Random r = new Random();
        this.firstNum = r.nextInt(99);
        this.secondNum = r.nextInt(99);
        if (this.firstNum < this.secondNum)
        {
            // We want to swap our digits to make sure we don't go negative for simplicity-sake.
            int tmp = this.firstNum;
            this.firstNum = this.secondNum;
            this.secondNum = tmp;
        }

        this.solution = this.firstNum - this.secondNum;

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
        return String.format("%d - %d = %s", firstNum, secondNum, (isSolved()) ? "" + solution : "?");
    }
}

