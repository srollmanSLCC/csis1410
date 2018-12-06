package problem;

import java.io.Serializable;
import java.util.Random;

public class Multiplication extends Problem
{
    public Multiplication ()
    {
        Random r = new Random();
        this.firstNum = r.nextInt(20);
        this.secondNum = r.nextInt(20);
        this.solution = firstNum * secondNum;
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
        return String.format("%d X %d = %s", firstNum, secondNum, (isSolved()) ? "" + solution : "?");
    }
}
