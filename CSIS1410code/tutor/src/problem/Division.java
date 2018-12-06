package problem;

import java.io.Serializable;
import java.util.Random;

public class Division extends Problem
{
    public Division ()
    {
        Random r = new Random();
        // ensure we don't get a 0 by adding 1
        this.secondNum = r.nextInt(9) + 1;
        this.firstNum = this.secondNum * r.nextInt(20);
        this.solution = firstNum / secondNum;
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
        return String.format("%d / %d = %s", firstNum, secondNum, (isSolved()) ? "" + solution : "?");
    }
}
