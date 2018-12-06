package problem;

import java.util.Random;

public class Subtraction extends Problem
{
    public Subtraction ()
    {
        Random r = new Random();
        this.firstNum = r.nextInt(99);
        this.secondNum = r.nextInt(99);
        this.solution = firstNum - secondNum;
        this.answer = -10000;
    }

    @Override
    public String helper()
    {
        return null;
    }

    @Override
    public boolean isSolved()
    {
        return this.solution == this.answer;
    }

    @Override
    public void setAnswer(int answer)
    {
        this.answer = answer;
    }

    @Override
    public String toString()
    {
        return String.format("%d - %d = %s", firstNum, secondNum, (isSolved()) ? "" + solution : "?");
    }
}

