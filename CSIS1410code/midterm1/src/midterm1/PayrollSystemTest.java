package midterm1;

// Author:          Steven Rollman
// Course:          CSIS 1410
// Date Created:    9/29/18
//PayrollSystemTest class driver for the payroll system

import java.text.NumberFormat;

public class PayrollSystemTest
{
    public static void main(String[] args)
    {
        Date today = new Date(10,8,2014);

        // create subclass objects
        SalariedEmployee salEmp = new SalariedEmployee(
                "John",
                "Smith",
                "111-11-1111",
                800,
                new Date(9,5,1975)
        );
        HourlyEmployee hourEmp = new HourlyEmployee(
                "Karen",
                "Price",
                "222-22-2222",
                16.75,
                40,
                new Date(10,27,1987)
        );
        CommissionEmployee comEmp = new CommissionEmployee(
                "Sue",
                "Jones",
                "333-33-3333",
                10000,
                .06,
                new Date(12,1,1990)
        );
        BasePlusCommissionEmployee bpcEmp = new BasePlusCommissionEmployee(
                "Bob",
                "Lewis",
                "444-44-4444",
                5000,
                0.04,
                300,
                new Date(10, 8, 1985)
        );
        // create four-element Employee array
        Employee[] employees = new Employee[4];

        // initialize array with Employees
        employees[0] = salEmp;
        employees[1] = hourEmp;
        employees[2] = comEmp;
        employees[3] = bpcEmp;

        /* calculate the payroll for each Employee (polymorphically),
         * and add a $100.00 bonus to the person�s payroll amount
         * if the current month is the one in which the Employee�s birthday occurs. */

        // Print out today's date first.
        System.out.println("Today's Date " + today.toString() + "\n");

        // Iterate through the employees array and generate output.
        for (Employee emp : employees)
        {
            // Generate a string using values accessible on the employee object.
            String employeeLines = String.format(
                    "employee: %s %s\nsocial security number: %s\nbirth date: %s",
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getSocialSecurityNumber(),
                    emp.getBirthDate()
            );

            String salaryLines;
            // Based on the employee's type, build the salary/pay portion of the string.
            if (emp instanceof SalariedEmployee)
            {
                SalariedEmployee sal = (SalariedEmployee) emp;
                employeeLines = "salaried " + employeeLines;
                salaryLines = String.format(
                        "weekly salary: $%.2f\npayroll: $%s",
                        sal.getWeeklySalary(),
                        sal.getBirthDate().getMonth() == today.getMonth() ?
                                // They need a birthday bonus.
                                String.format(
                                        "%.2f + birthday bonus: $%.2f = $%.2f",
                                        sal.earnings(),
                                        100.00,
                                        sal.earnings() + 100
                                ) :
                                // They don't need a birthday bonus.
                                String.format("%.2f", sal.earnings())
                );
            }
            else if (emp instanceof HourlyEmployee)
            {
                HourlyEmployee hr = (HourlyEmployee) emp;
                employeeLines = "hourly " + employeeLines;
                salaryLines = String.format(
                        "hourly wage: $%.2f; hours worked: %.2f\npayroll: $%s",
                        hr.getWage(),
                        hr.getHours(),
                        hr.getBirthDate().getMonth() == today.getMonth() ?
                                // They need a birthday bonus.
                                String.format(
                                        "%.2f + birthday bonus: $%.2f = $%.2f",
                                        hr.earnings(),
                                        100.00,
                                        hr.earnings() + 100
                                ) :
                                // They don't need a birthday bonus.
                                String.format("%.2f", hr.earnings())
                );
            }
            else if (emp instanceof BasePlusCommissionEmployee)
            {
                BasePlusCommissionEmployee bemp = (BasePlusCommissionEmployee) emp;
                employeeLines = "base-salaried commission " + employeeLines;
                salaryLines = String.format(
                        "gross sales: %s; commission rate: %.2f; base salary: $%.2f\npayroll: $%s",
                        NumberFormat.getCurrencyInstance().format(bemp.getGrossSales()),
                        bemp.getCommissionRate(),
                        bemp.getBaseSalary(),
                        bemp.getBirthDate().getMonth() == today.getMonth() ?
                                // They need a birthday bonus.
                                String.format(
                                        "%.2f + birthday bonus: $%.2f = $%.2f",
                                        bemp.earnings(),
                                        100.00,
                                        bemp.earnings() + 100
                                ) :
                                // They don't need a birthday bonus.
                                String.format("%.2f", bemp.earnings())
                );
            }
            else
            {
                CommissionEmployee cemp = (CommissionEmployee) emp;
                employeeLines = "commission " + employeeLines;
                salaryLines = String.format(
                        "gross sales: %s; commission rate: %.2f\npayroll: $%s",
                        NumberFormat.getCurrencyInstance().format(cemp.getGrossSales()),
                        cemp.getCommissionRate(),
                        cemp.getBirthDate().getMonth() == today.getMonth() ?
                                // They need a birthday bonus.
                                String.format(
                                        "%.2f + birthday bonus: $%.2f = $%.2f",
                                        cemp.earnings(),
                                        100.00,
                                        cemp.earnings() + 100
                                ) :
                                // They don't need a birthday bonus.
                                String.format("%.2f", cemp.earnings())
                );
            }

            // Print the output.
            System.out.printf("%s\n%s\n\n",employeeLines, salaryLines);
        }

    } // end main
} // end class PayrollSystemTest

