package problem;

import java.util.Comparator;

public class ProblemComparator implements Comparator
{
    @Override
    public int compare(Object one, Object two)
    {
        if (one instanceof Problem && two instanceof Problem)
        {
            Problem first = (Problem) one;
            Problem second = (Problem) two;

            if ((first.isSolved() && second.isSolved()) || (!first.isSolved() && !second.isSolved()))
            {
                return 0;
            }
            else if (!first.isSolved())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
        return 0;
    }
}
