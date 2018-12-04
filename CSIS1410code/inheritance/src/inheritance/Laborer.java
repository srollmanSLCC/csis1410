package inheritance;

/********************************************************
 *
 *  Project :  Inheritance
 *  File    :  Laborer.java
 *  Name    :  Steven Rollman
 *  Date    :  9/8/2018
 *
 *  Description : Class Laborer that extends the employee class.
 *
 *  Changes :
 *
 ********************************************************/

/**
 * Laborer class that extends Employee.
 */
public class Laborer extends Employee
{
    private int age;
    private double hourlyWage;

    /**
     * No args constructor.
     */
    public Laborer()
    {
        this(null, null, -1, 0, 0);
    }

    /**
     * Constructor for a laborer employee that takes 5 arguments.
     *
     * @param firstName  The first name of the Employee.
     * @param lastName   The last name of the Employee.
     * @param idNumber   The Id of the Employee.
     * @param age        The age of the Laborer.
     * @param hourlyWage The Hourly-wage of the Laborer Employee.
     */
    public Laborer(String firstName, String lastName, int idNumber, int age, double hourlyWage)
    {
        super(firstName, lastName, 0, idNumber);

        setAge(age);
        setHourlyWage(hourlyWage);
    }

    /**
     * Getter method for Age property
     *
     * @return An Integer representing the Age of this Laborer.
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Setter method for the age property.
     *
     * @param age The age to set for this Laborer Employee.
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Getter method for the Hourly Wage property
     *
     * @return A double representing the Age of this Laborer.
     */
    public double getHourlyWage()
    {
        return hourlyWage;
    }

    /**
     * Setter method for the hourly wage property.
     *
     * @param hourlyWage The hourly wage to set for this Laborer Employee.
     */
    public void setHourlyWage(double hourlyWage)
    {
        this.hourlyWage = hourlyWage;
    }

    /**
     * Overridden toString method to represent this Laborer Employee.
     * @return A String representation of this Laborer Employee.
     */
    @Override
    public String toString()
    {
        return String.format(
                "First: %s\tLast: %s\tId: %d\tAge: %d\tWage: $%.2f/hr\tEmployee Type: Laborer\nThis is the toString() for the super class:%s",
                this.getFirstName(),
                this.getLastName(),
                this.getIDNumber(),
                getAge(),
                getHourlyWage(),
                super.toString()
        );
    }
}
