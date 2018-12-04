package M1A2;

// Author:          Steven Rollman
// Course:          CSIS 1410
// Date Created:    8/28/18

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParkingChargesTest
{

    @Test
    public void calcParkingCharges()
    {
        ParkingCharges charges = new ParkingCharges();

        double value = charges.calcParkingCharges(1.3);
        System.out.printf("$%.2f\n", value);
        assertEquals(value, 2, 0);

        value = charges.calcParkingCharges(12);
        assertEquals(value, 6.5, 0);

        value = charges.calcParkingCharges(18);
        assertEquals(value, 9.5, 0);

        value = charges.calcParkingCharges(19.3);
        assertEquals(value, 10, 0);

        value = charges.calcParkingCharges(9);
        assertEquals(value, 5, 0);
    }
}