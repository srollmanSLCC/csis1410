package inheritance;

/********************************************************
 *
 *  Project :  Inheritance
 *  File    :  Sales.java
 *  Name    :  Steven Rollman
 *  Date    :  9/8/2018
 *
 *  Description : Class Sales that extends the employee class.
 *
 *  Changes :
 *
 ********************************************************/

/**
 * Sales class that extends Employee.
 */
public class Sales extends Employee
{
    private double commissionRate;

    /**
     * No args constructor.
     */
    public Sales()
    {
        this(null, null, -1, 0.0, 0.0);
    }

    /**
     * Sales constructor with 5 arguments. Passes args onto super class.
     *
     * @param firstName      The first name of the Employee.
     * @param lastName       The last name of the Employee.
     * @param idNumber       The Id of the Employee.
     * @param pay            The base pay of the Employee.
     * @param commissionRate The commission rate for the Sales Employee.
     */
    public Sales(String firstName, String lastName, int idNumber, double pay, double commissionRate)
    {
        super(firstName, lastName, pay, idNumber);
        setCommissionRate(commissionRate);
    }

    /**
     * Getter for commissionRate property.
     *
     * @return A double representing the commission rate for the Sales Employee.
     */
    public double getCommissionRate()
    {
        return commissionRate;
    }

    /**
     * Setter for commissionRate property.
     *
     * @param commissionRate The commissionRate to set for the Sales Employee
     */
    public void setCommissionRate(double commissionRate)
    {
        this.commissionRate = commissionRate;
    }

    /**
     * Overridden toString method to represent this Laborer Employee.
     *
     * @return A String representation of this Laborer Employee.
     */
    @Override
    public String toString()
    {
        return String.format(
                "First: %s\tLast: %s\tBase Pay: $%.2f\tId: %d\tCommission Rate: %.2f%%\tEmployee Type: Sales",
                this.getFirstName(),
                this.getLastName(),
                this.getPay(),
                this.getIDNumber(),
                getCommissionRate()
        );
    }
}
