package problem;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable
{

    private String name;
    private ArrayList<Problem> problems;
    private int mostRecentProblemIndex;

    public Student()
    {
        this.name = null;
        this.problems = new ArrayList<>();
    }

    public Student(String name)
    {
        this.name = name;
        this.problems = new ArrayList<>();
    }

    public Student (String name, Iterable<Problem> problems)
    {
        this.name = name;
        this.problems = new ArrayList<>();
        for (Problem prob : problems)
        {
            this.problems.add(prob);
        }
    }

    public void addProblem(Problem.problemTypes type)
    {
        Problem p = createProblem(type);
        this.problems.add(p);
        this.problems.sort(new ProblemComparator());
        mostRecentProblemIndex = this.problems.indexOf(p);
    }

    private Problem createProblem(Problem.problemTypes type)
    {
        Problem newProblem;
        switch (type)
        {
            case SUBTRACTION:
                newProblem = new Subtraction();
                break;
            case MULTIPLICATION:
                newProblem = new Multiplication();
                break;
            case DIVISION:
                newProblem = new Division();
                break;
            case ADDITION:
            default:
                newProblem = new Addition();
                break;
        }

        return newProblem;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String value)
    {
        if (value != null && value != "" && value.matches("^([A-Za-z]+ )+[A-Za-z]+$|^[A-Za-z]+$"))
        {
            this.name = value;
        }
    }

    public int getMostRecentProblemIndex()
    {
        return this.mostRecentProblemIndex;
    }

    public boolean hasProblems()
    {
        return this.problems.size() > 0;
    }

    public ArrayList<Problem> getProblems()
    {
        return this.problems;
    }
}
