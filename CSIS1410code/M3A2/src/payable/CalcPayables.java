/**
 * CSIS1410
 * Interface example
 */

package payable;

import employees.*;
import invoice.Invoice;

/**
 * @author rbaird
 *
 */
public class CalcPayables
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
    	// create an array of 5 Payable references
        Payable[] payables = new Payable[5];
    	// fill the array with references to Employee and Invoice objects
        for (int i = 0; i < 5; i++)
        {
            switch (i)
            {
                case 0:
                    payables[i] = new Invoice("Bill's Vending", "Sodas", 4.99, 256);
                    break;
                case 1:
                    payables[i] = new CommissionedEmployee("Dem Sales", 1, 1000, 12, 250);
                    break;
                case 2:
                    payables[i] = new HourlyEmployee("Moe Hoursplease", 2, 90, 11.75);
                    break;
                case 3:
                    payables[i] = new SalariedEmployee("Noah Clockin", 3, 1234.56, 1);
                    break;
                case 4:
                    payables[i] = new HourlyEmployee("Fulltime Student", 4, 8, 7.25);
                    break;
            }
        }
    	// print the payables
        for (Payable payable : payables)
        {
            System.out.println(payable.toString());
        }
    }
}
