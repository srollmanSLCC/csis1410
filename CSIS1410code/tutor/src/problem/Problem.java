package problem;

import java.io.Serializable;

public abstract class Problem implements Serializable
{
    public enum problemTypes
    {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
    }

    public int firstNum;
    public int secondNum;
    public int solution;
    public int answer;
    public abstract String helper();
    public abstract boolean isSolved();
    public abstract void setAnswer(int answer);
}
