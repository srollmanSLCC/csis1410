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
        // new problems get added at the beginning of the arraylist.
        this.problems.add(0,p);
        mostRecentProblemIndex = this.problems.indexOf(p);
    }

    public int getMostRecentProblemIndex()
    {
        return this.mostRecentProblemIndex;
    }

    public String getName()
    {
        return this.name;
    }

    public ArrayList<Problem> getProblems()
    {
        return this.problems;
    }

    public boolean hasProblems()
    {
        return this.problems.size() > 0;
    }

    public void setMostRecentProblemIndex(int mostRecentProblemIndex)
    {
        this.mostRecentProblemIndex = mostRecentProblemIndex;
    }

    public void setName(String value)
    {
        if (value != null && value != "" && value.matches("^([A-Za-z]+ )+[A-Za-z]+$|^[A-Za-z]+$"))
        {
            this.name = value;
        }
    }

    public boolean trySolveProblem(int problemToSolve, int potentialSolution) throws IndexOutOfBoundsException
    {
        Problem p = problems.get(problemToSolve);
        if (p == null)
        {
            // We didn't get the right index, obviously.
            throw new IndexOutOfBoundsException();
        }

        p.setAnswer(potentialSolution);

        if (p.isSolved())
        {
            setMostRecentProblemIndex(refreshProblems(true));
        }
        return p.isSolved();

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

    /**
     * Refresh the problems arrayList to maintain a good order of unsolved -> solved in the order they were added/solved.
     * @return an int representing the index of a problem that was recently solved and was moved.
     */
    private int refreshProblems(boolean triggeredOnSolved)
    {
        if (!hasProblems())
        {
            return -1;
        }
        int lastUnsolved = -1;
        int firstSolved = -1;
        for (int i = 0; i < problems.size(); i++)
        {
            if (!problems.get(i).isSolved())
            {
                // if it's unsolved, set lastUnsolved = i;
                lastUnsolved = i;
            }
            else if (firstSolved == -1)
            {
                firstSolved = i;
            }
        }

        // if we are ordered correctly, but we know we solved one, return the index of the first solved.
        if ((lastUnsolved < firstSolved) && triggeredOnSolved)
        {
            return firstSolved;
        }
        // if our firstSolved < lastUnsolved, we have solved a problem since we last refreshed.
        else if (firstSolved < lastUnsolved || triggeredOnSolved)
        {
            while (firstSolved < lastUnsolved)
            {
                firstSolved = shiftRight(firstSolved);
            }
            return firstSolved;
        }
        else
        {
            return -1;
        }
    }

    private int shiftRight(int indexToShift)
    {
        Problem toMoveLeft = problems.get(indexToShift + 1);
        problems.set(indexToShift + 1, problems.get(indexToShift));
        problems.set(indexToShift, toMoveLeft);
        return indexToShift + 1;
    }
}
