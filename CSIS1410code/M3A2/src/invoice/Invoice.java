/**
 * CSIS1410
 * Interface example
 */

package invoice;

import payable.Payable;
import java.text.DecimalFormat;

/**
 * @author rbaird
 *
 */
public class Invoice implements Payable
{
    private String vendor;
    private String item;
    private double price;
    private int quantity;

    /**
     * @param vendor
     * @param item
     * @param price
     * @param quantity
     */
    public Invoice(String vendor, String item, double price, int quantity)
    {
    	this.vendor = vendor;
    	this.item = item;
    	this.price = price;
    	this.quantity = quantity;
    }

    @Override
    public double getPay()
    {
    	return price * quantity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
    	DecimalFormat df = new DecimalFormat("$###,###.##");
    	return "Invoice:\n" + " Vendor: " + vendor + "\n item: " + item + "\n price: " + price + "\n quantity: "
    		+ quantity + "\n bill: " + df.format(getPay()) + "\n";
    }

}
