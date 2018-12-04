package craps;//CS1410
//Play Craps game

import java.util.Scanner;

/**
 * This class initializes and runs a game of Craps
 *
 * @see Object
 */
public class CrapsGame
{
    /**
     * Main method of the CrapsGame program.
     * Initializes a Scanner and starts the game.
     * @param args Command line arguments
     */
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        char again = 'Y';
        Craps game = new Craps();

        do
        {
            game.play(); // play one game of craps
            System.out.print("\nPlay again (Y/N): ");
            again = Character.toUpperCase(input.nextLine().charAt(0));
            System.out.println();
        }
        while (again == 'Y');
    } // end main
} // end class CrapsTest
