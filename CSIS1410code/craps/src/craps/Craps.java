package craps;// Craps class simulates the dice game craps.


/**
 * This class plays a game of Craps.
 *
 * @see Object
 */
public class Craps
{
    // enumeration with constants that represent the game status
    private enum Status
    {
        CONTINUE, WON, LOST
    }

    // constants that represent common rolls of the dice
    private final static int SNAKE_EYES = 2;
    private final static int TREY = 3;
    private final static int SEVEN = 7;
    private final static int YO_LEVEN = 11;
    private final static int BOX_CARS = 12;

    // die
    private Die die1 = new Die();
    private Die die2 = new Die();

    /**
     * Plays one game of Craps
     */
    public void play()
    {
        int myPoint = 0; // point if no win or loss on first roll
        Status gameStatus; // can contain CONTINUE, WON or LOST

        int sumOfDice = rollDice(); // first roll of the dice

        // determine game status and point based on first roll
        switch (sumOfDice)
        {
            case SEVEN: // win with 7 on first roll
            case YO_LEVEN: // win with 11 on first roll
                gameStatus = Status.WON;
                break;
            case SNAKE_EYES: // lose with 2 on first roll
            case TREY: // lose with 3 on first roll
            case BOX_CARS: // lose with 12 on first roll
                gameStatus = Status.LOST;
                break;
            default: // did not win or lose, so remember point
                gameStatus = Status.CONTINUE; // game is not over
                myPoint = sumOfDice; // remember the point
                System.out.printf("Point is %d\n", myPoint);
                break; // optional at end of switch
        } // end switch

        // while game is not complete
        while (gameStatus == Status.CONTINUE) // not WON or LOST
        {
            sumOfDice = rollDice(); // roll dice again

            // determine game status
            if (sumOfDice == myPoint) // win by making point
                gameStatus = Status.WON;
            else if (sumOfDice == SEVEN) // lose by rolling 7 before point
                gameStatus = Status.LOST;
        } // end while

        // display won or lost message
        if (gameStatus == Status.WON)
            System.out.println("Player wins");
        else
            System.out.println("Player loses");
    } // end method play

    /**
     * Rolls two dice.
     *
     * @see Die
     * @return an <code>integer</code> that represents the sum of the two dice rolled.
     */
    public int rollDice()
    {
        // roll each die
        die1.roll();
        die2.roll();

        // sum of die values
        int sum = die1.getValue() + die2.getValue();

        // display results of this roll
        System.out.printf("Player rolled %d + %d = %d\n",
                die1.getValue(), die2.getValue(), sum);

        return sum; // return sum of dice
    } // end method rollDice
} // end class Craps

