/**
 * CSIS1410
 * Interface example
 */

package employees;

public class EmployeeApp
{

	public static void main(String[] args)
	{
		// Employee emp = new Employee("Jack", 1001, 50000);
		// System.out.println(emp);

		/*
		 * SalariedEmployee sEmp = new SalariedEmployee("Jill", 1002, 4000, 27);
		 * System.out.println(sEmp.toString());
		 * System.out.println(sEmp.getIdNumber());
		 * System.out.println(sEmp.getVacationDays());
		 * //System.out.println(sEmp.hashCode());
		 * 
		 * SalariedEmployee sEmpx = new SalariedEmployee("Jill", 1002, 4000,
		 * 27); System.out.println(sEmp.hashCode());
		 * System.out.println(sEmpx.hashCode());
		 * System.out.println(sEmp.equals(sEmpx)); System.out.println();
		 * 
		 * CommissionedEmployee cEmp = new CommissionedEmployee("John", 1003,
		 * 0.0, 10000, 0.45); cEmp.getPay(); System.out.println(cEmp);
		 * 
		 * HourlyEmployee hEmp = new HourlyEmployee("Joan", 1004, 42, 20.0);
		 * hEmp.getPay(); System.out.println(hEmp);
		 */

		Employee[] empList = new Employee[3];

		empList[0] = new SalariedEmployee("Jill", 1002, 4000, 27);
		empList[1] = new CommissionedEmployee("John", 1003, 0.0, 10000, 0.45);
		empList[2] = new HourlyEmployee("Joan", 1004, 42, 20.0);

		for (int i = 0; i < empList.length; i++)
		{
			empList[i].getPay();
			System.out.println(empList[i].toString());
		}
	}

}
