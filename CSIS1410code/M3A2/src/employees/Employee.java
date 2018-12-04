/**
 * CSIS1410
 * Interface example
 */

package employees;

import java.text.DecimalFormat;

import payable.Payable;

abstract public class Employee implements Payable
{

    private String name;
    private int idNumber;
    protected double pay;

    public Employee(String name, int idNumber, double pay)
    {
    	super();
    	this.name = name;
    	this.idNumber = idNumber;
    	this.pay = pay;
    }

    @Override
    public int hashCode()
    {
    	final int prime = 31;
    	int result = 1;
    	result = prime * result + idNumber;
    	return result;
    }

    @Override
    public boolean equals(Object obj)
    {
    	if (this == obj)
    	    return true;
    	if (obj == null)
    	    return false;
    	if (getClass() != obj.getClass())
    	    return false;
    	Employee other = (Employee) obj;
    	if (idNumber != other.idNumber)
    	    return false;
    	return true;
    }

    public String getName()
    {
    	return name;
    }

    public void setName(String name)
    {
    	this.name = name;
    }

    public int getIdNumber()
    {
    	return idNumber;
    }

    public void setIdNumber(int idNumber)
    {
    	this.idNumber = idNumber;
    }

    abstract public double getPay();

    public void setPay(double pay)
    {
    	this.pay = pay;
    }

    @Override
    public String toString()
    {
    	DecimalFormat df = new DecimalFormat("$###,###.##");
    	return "Name: " + name + "\n idNumber: " + idNumber + "\n pay: " + df.format(getPay()) + "\n";
    }

}
