/**
 * CSIS1410
 * Interface example
 */

package employees;

/**
 * @author rbaird
 *
 */
public class HourlyEmployee extends Employee
{

    double hoursWorked;
    double hourlyRate;

    /**
     * @param name
     * @param idNumber
     * @param pay
     */
    public HourlyEmployee(String name, int idNumber, double hours, double rate)
    {
    	super(name, idNumber, 0.0);
    	hoursWorked = hours;
    	hourlyRate = rate;
    }

    /**
     * @return the hoursWorked
     */
    public double getHoursWorked()
    {
    	return hoursWorked;
    }

    /**
     * @param hoursWorked
     *            the hoursWorked to set
     */
    public void setHoursWorked(double hoursWorked)
    {
    	this.hoursWorked = hoursWorked;
    }

    /**
     * @return the hourlyRate
     */
    public double getHourlyRate()
    {
    	return hourlyRate;
    }

    /**
     * @param hourlyRate
     *            the hourlyRate to set
     */
    public void setHourlyRate(double hourlyRate)
    {
    	this.hourlyRate = hourlyRate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see employees.Employee#getPay()
     */
    @Override
    public double getPay()
    {
    	if (hoursWorked > 40)
    	{
    	    double OTHours = hoursWorked - 40;
    	    double regHours = 40;
    	    setPay(1.5 * hourlyRate * OTHours + hourlyRate * regHours);
    	} else
    	{
    	    setPay(hoursWorked * hourlyRate);
    	}
    
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
    	return String.format("Hourly Employee\n %s", super.toString());
    }

}
