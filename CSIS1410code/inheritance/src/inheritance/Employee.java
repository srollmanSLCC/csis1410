package inheritance;

/********************************************************
 *
 *  Project :  Inheritance
 *  File    :  Employee.java
 *  Name    :  Steven Rollman
 *  Date    :  9/8/2018
 *
 *  Description : Abstract class employee.
 *
 *  Changes :
 *
 ********************************************************/

/**
 * Abstract Employee class.
 */
public abstract class Employee
{
    private String firstName;
    private String lastName;
    private double pay;
    private int idNumber;

    /**
     * No args constructor.
     */
    public Employee()
    {
        this("", "", 0.0, -1);
    }//default constructor

    /**
     * Constrcutor for Employee that takes 4 arguments.
     *
     * @param firstName The first name of the Employee.
     * @param lastName  The last name of the Employee.
     * @param pay       The salary of the Employee.
     * @param idNumber  The Id of the Employee.
     */
    public Employee(
            String firstName,
            String lastName,
            double pay,
            int idNumber
    )
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pay = pay;
        this.idNumber = idNumber;
    }//normal constructor

    /**
     * Setter method for the first name property.
     *
     * @param name The first name of the Employee.
     */
    public void setFirstName(String name)
    {
        this.firstName = name;
    }//end setFirstName()

    /**
     * Setter method for the last name property.
     *
     * @param name The last name of the Employee.
     */
    public void setLastName(String name)
    {
        lastName = name;
    }//end setLastName()

    /**
     * Setter method for the pay property.
     *
     * @param p The pay of the Employee.
     */
    public void setPay(double p)
    {
        if (p < 0)
        {
            return;
        }
        pay = p;
    }//end setPay()

    /**
     * Setter method for the Id property.
     *
     * @param id The Id of the Employee.
     */
    public void setIDNumber(int id)
    {
        idNumber = id;
    }//end setIDNumber()

    /**
     * Getter method for the first name property.
     *
     * @return A String representing the first name of this Employee.
     */
    public String getFirstName()
    {
        return firstName;
    }//end getFirstName()

    /**
     * Getter method for the last name property.
     *
     * @return A String representing the last name of this Employee.
     */
    public String getLastName()
    {
        return lastName;
    }//end getLastName()

    /**
     * Getter method for the pay property.
     *
     * @return A double representing the pay of this Employee.
     */
    public double getPay()
    {
        return pay;
    }//end getPay(

    /**
     * Getter method for the Id property.
     *
     * @return An Integer representing the first name of this Employee.
     */
    public int getIDNumber()
    {
        return idNumber;
    }//end getIDNumber()

    /**
     * Overridden toString method that represents this Employee.
     *
     * @return This Employee represented as a String.
     */
    @Override
    public String toString()
    {
        return String.format("%s\t%s\t%.2f\t%d", getFirstName(), getLastName(), getPay(), getIDNumber());
    }//end toString()

}//end class Employee