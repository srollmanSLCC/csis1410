package inheritance;

/********************************************************
 *
 *  Project :  Inheritance
 *  File    :  Manager.java
 *  Name    :  Steven Rollman
 *  Date    :  9/8/2018
 *
 *  Description : Class Manager that extends the employee class.
 *
 *  Changes :
 *
 ********************************************************/

/**
 * Manager class that extends Employee.
 */
public class Manager extends Employee
{
    private String title;
    private int bonus;

    /**
     * No args constructor.
     */
    public Manager()
    {
        this(null, null, 0, -1, null, 0);
    }

    /**
     * Constructor that takes 6 arguments for a Manager Employee.
     *
     * @param firstName First name of the Employee.
     * @param lastName  Last name of the Employee.
     * @param pay       The salary of the Employee.
     * @param idNumber  The Id of the Employee.
     * @param title     The job title of the Manager Employee.
     * @param bonus     The bonus of the Manager Employee.
     */
    public Manager(String firstName, String lastName, double pay, int idNumber, String title, int bonus)
    {
        super(firstName, lastName, pay, idNumber);
        setTitle(title);
        setBonus(bonus);
    }

    /**
     * Getter for Title property.
     *
     * @return A String representing the title of the Manager Employee.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Setter for Title property.
     *
     * @param title String to set as the Title for this Manager Employee.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Getter for Bonus property.
     *
     * @return An Integer representing the bonus of the Manager Employee.
     */
    public int getBonus()
    {
        return bonus;
    }

    /**
     * Setter for Bonus property.
     *
     * @param bonus Integer to set as the Bonus for this Manager Employee.
     */
    public void setBonus(int bonus)
    {
        this.bonus = bonus;
    }

    /**
     * Overridden toString method to represent this Manager Employee.
     * @return A String representation of this Manager Employee.
     */
    @Override
    public String toString()
    {
        return String.format(
                "First: %s\tLast: %s\tPay: $%.2f\tId: %d\tTitle: %s\tBonus: $%d\tEmployee Type: Manager",
                this.getFirstName(),
                this.getLastName(),
                this.getPay(),
                this.getIDNumber(),
                getTitle(),
                getBonus()
        );
    }
}
