package inheritance;

/********************************************************
 *
 *  Project :  Inheritance
 *  File    :  EmployeeTest.java
 *  Name    :  Steven Rollman
 *  Date    :  9/8/2018
 *
 *  Description : Tests the hierarchy of inheritance for Employee objects.
 *
 *  Changes :
 *
 ********************************************************/

public class EmployeeTest
{
    /**
     * Main method for the inheritance program.
     * @param args Arguments from the command line to pass to the program.
     */
    public static void main(String[] args)
    {
        Manager m1 = new Manager("Bill", "Smith", 5000.0, 1234, "The Boss", 250);
        Employee m2 = new Manager("Suzy", "Jones", 6000.0, 3456, "The Real Boss", 300);
        Sales s1 = new Sales("Tom", "White", 4567, 20000.0, 0.12);
        Employee s2 = new Sales("Lucy", "Green", 5678, 35000.0, 0.15);
        Laborer l1 = new Laborer("Joey", "Black", 7890, 37, 12.25);

        System.out.println("Here are the employees...");
        System.out.println(m1.toString());
        System.out.println(m2);
        System.out.println(s1.toString());
        System.out.println(s2);
        System.out.println(l1);
    }// end main()
}// end EmployeeTest