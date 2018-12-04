package craps;

import java.util.Random;

/**
 * This class is a code representation of a single, 6-sided die.
 * @see Object
 */
class Die
{
    //class variables
    private int dieValue_;
    public final static int MAX_DIE_VALUE = 6;
    public final static int MIN_DIE_VALUE = 1;
    private Random r;

    /**
     * Die no-argument constructor initializes value to the minimum value
     * and initializes a new instance of the <code>Random</code> class
     */
    Die()
    {
        dieValue_ = MIN_DIE_VALUE;
        r = new Random();
    }

    /**
     * Simulates the roll of a single, 6-sided die.
     */
    public void roll()
    {
        dieValue_ = MIN_DIE_VALUE + r.nextInt(MAX_DIE_VALUE);
        //dieValue_ = (int)(MIN_DIE_VALUE + Math.random() * MAX_DIE_VALUE);
    }

    /**
     * Returns the value of the last roll.
     *
     * @return an <code>integer</code> representing the value of the most-recent die roll.
     */
    public int getValue()
    {
        return dieValue_;
    }

    /**
     * ToString implementation for Die
     *
     * @return a <code>String</code> that represents the value of the most-recent die roll.
     */
    public String toString()
    {
        return "%d" + dieValue_;
    }
}

