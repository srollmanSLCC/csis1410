package M1A2;

// Author:          Steven Rollman
// Course:          CSIS 1410
// Date Created:    8/28/18

public class ParkingCharges
{
    private final double MIN_CHARGE = 2.00;
    private final double MAX_CHARGE = 10.00;

    public double calcParkingCharges(double hoursParked)
    {
        int hours = (int) Math.ceil(hoursParked);

        if (hours < 3)
        {
            return MIN_CHARGE;
        }

        int billHours = hours - 3;
        double totalCharge = (billHours * .5) + MIN_CHARGE;

        return totalCharge < MAX_CHARGE ?
                totalCharge :
                MAX_CHARGE;
    }
}
