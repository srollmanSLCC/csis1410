package M1A2;

// Author:          Steven Rollman
// Course:          CSIS 1410
// Date Created:    8/28/18

import java.util.Scanner;

public class ParkingChargesApp
{
    public static void main(String[] args)
    {
        RunApp();
    }

    private static void RunApp()
    {
        ParkingCharges charges = new ParkingCharges();
        double sum = 0;

        while (true)
        {
            Scanner reader = new Scanner(System.in);
            System.out.println("Is there a customer waiting to pay? (Y/N)");
            String in = reader.nextLine();
            if (in.equalsIgnoreCase("y"))
            {
                boolean hasValidValue = false;
                double hours = 0;
                System.out.println("How many hours are on their ticket?");
                while (!hasValidValue)
                {
                    try
                    {
                        hours = reader.nextDouble();
                        if (hours > 0)
                        {
                            hasValidValue = true;
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println("Invalid entry, please enter the total time on the customer's ticket");
                    }
                }

                double due = charges.calcParkingCharges(hours);
                System.out.printf("Customer owes $%.2f.\n", due);
                sum += due;
                System.out.printf("Today's total revenue: $%.2f\n", sum);
            }
            else
            {
                System.out.printf("You made a total of $%.2f today. Goodbye!\n", sum);
                return;
            }
        }
    }
}
