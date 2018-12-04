/**
 * CSIS1410
 * Interface example
 */
package employees;

/**
 * @author rbaird
 *
 */
public class CommissionedEmployee extends Employee
{

    private double salesVolume;
    private double commissionRate;

    /**
     * @param name
     * @param idNumber
     * @param pay
     */
    public CommissionedEmployee(String name, int idNumber, double pay, double volume, double rate)
    {
    	super(name, idNumber, pay);
    	salesVolume = volume;
    	commissionRate = rate;
    }

    /**
     * @return the salesVolume
     */
    public double getSalesVolume()
    {
    	return salesVolume;
    }

    /**
     * @param salesVolume
     *            the salesVolume to set
     */
    public void setSalesVolume(double salesVolume)
    {
    	this.salesVolume = salesVolume;
    }

    /**
     * @return the commissionRate
     */
    public double getCommissionRate()
    {
    	return commissionRate;
    }

    /**
     * @param commissionRate
     *            the commissionRate to set
     */
    public void setCommissionRate(double commissionRate)
    {
    	this.commissionRate = commissionRate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see employees.Employee#getPay()
     */
    @Override
    public double getPay()
    {
    	super.setPay(getSalesVolume() * getCommissionRate());
    	return pay;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
    	return String.format("CommissionedEmployee\n %s", super.toString());
    }

}
