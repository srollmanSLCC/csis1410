/**
 * CSIS1410
 * Interface example
 */

package employees;

public class SalariedEmployee extends Employee
{

    private int vacationDays;

    public SalariedEmployee(String name, int idNumber, double pay, int vacationDays)
    {
    	super(name, idNumber, pay);
    	this.vacationDays = vacationDays;
    }

    public int getVacationDays()
    {
    	return vacationDays;
    }

    public void setVacationDays(int vacationDays)
    {
    	this.vacationDays = vacationDays;
    }

    public double getPay()
    {
    	return pay;
    }

    @Override
    public String toString()
    {
    	return String.format("SalariedEmployee\n %s", super.toString());
    }

}
