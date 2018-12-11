package problem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Public class Student
 */
public class Student implements Serializable
{

    private String name;
    private ArrayList<Problem> problems;
    private int mostRecentProblemIndex;

    /**
     * Default constructor.
     */
    public Student()
    {
        this.name = null;
        this.problems = new ArrayList<>();
    }

    /**
     * Constructor with name argument.
     * @param name the name of the student
     */
    public Student(String name)
    {
        this.name = name;
        this.problems = new ArrayList<>();
    }

    /**
     * Constructor with name and problems argument
     * @param name the name of the student.
     * @param problems a collection of problems.
     */
    public Student (String name, Iterable<Problem> problems)
    {
        this.name = name;
        this.problems = new ArrayList<>();
        for (Problem prob : problems)
        {
            this.problems.add(prob);
        }
    }

    /**
     * Add a problem to the collection.
     * @param type
     */
    public void addProblem(Problem.problemTypes type)
    {
        Problem p = createProblem(type);
        // new problems get added at the beginning of the arraylist.
        this.problems.add(0,p);
        mostRecentProblemIndex = this.problems.indexOf(p);
    }

    /**
     * Return the index of the most recent problem we added/worked on.
     * @return the index.
     */
    public int getMostRecentProblemIndex()
    {
        return this.mostRecentProblemIndex;
    }

    /**
     * Gets the name of this student.
     * @return the name of this student.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Gets a list of problems for this student.
     * @return this Student's problems.
     */
    public ArrayList<Problem> getProblems()
    {
        return this.problems;
    }

    /**
     * Gets a new problem.
     * @param type the type of problem to get.
     * @return the problem.
     */
    static Problem getNewProblem(Problem.problemTypes type)
    {
        Problem tutorProblem;
        switch (type)
        {
            case SUBTRACTION:
                tutorProblem = new Subtraction();
                break;

            case MULTIPLICATION:
                tutorProblem = new Multiplication();
                break;

            case DIVISION:
                tutorProblem = new Division();
                break;

            case ADDITION:
            default:
                tutorProblem = new Addition();
                break;
        }
        return tutorProblem;
    }

    /**
     * Whether or not this student has problems.
     * @return true if this student has problems.
     */
    public boolean hasProblems()
    {
        return this.problems.size() > 0;
    }

    /**
     * Sets the index of the most-recent problem for the student.
     * @param mostRecentProblemIndex the index.
     */
    public void setMostRecentProblemIndex(int mostRecentProblemIndex)
    {
        this.mostRecentProblemIndex = mostRecentProblemIndex;
    }

    /**
     * Set the name of the student.
     * @param value this student's new name.
     */
    public void setName(String value)
    {
        if (value != null && value != "" && value.matches("^([A-Za-z]+ )+[A-Za-z]+$|^[A-Za-z]+$"))
        {
            this.name = value;
        }
    }

    /**
     * Check if the provided potential solution solves the provided problem.
     * @param problemToSolve The index of the problem to solve.
     * @param potentialSolution The potential solution to the problem.
     * @return true if the potential solution is the solution.
     * @throws IndexOutOfBoundsException
     */
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

    /**
     * Create a new problem.
     * @param type the type of problem
     * @return the problem
     */
    private Problem createProblem(Problem.problemTypes type)
    {
        return getNewProblem(type);
    }

    /**
     * Refresh the problems arrayList to maintain a good order of unsolved to solved in the order they were added/solved.
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

    /**
     * Manages the list of problems to keep it in order.
     * @param indexToShift the index to swap places with the index to the right.
     * @return the new index of the provided index.
     */
    private int shiftRight(int indexToShift)
    {
        Problem toMoveLeft = problems.get(indexToShift + 1);
        problems.set(indexToShift + 1, problems.get(indexToShift));
        problems.set(indexToShift, toMoveLeft);
        return indexToShift + 1;
    }
}
