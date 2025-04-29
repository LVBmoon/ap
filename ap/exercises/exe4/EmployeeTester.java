package ap.exercises.exe4;

public class EmployeeTester {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Hacker, Harry", 1000);
        employee1.raiseSalary(10);

        double raisedSalary = employee1.getSalary();
        System.out.println("\nThe expected details: \nName: Hacker, Harry\nSalary: $1100.0\n--");
        System.out.println("The code result: " + "\nName: " + employee1.getName() + "\nSalary: $" + raisedSalary);
        System.out.println("---------------------------------");

        Employee newEmployee = new Employee("LVB, moon", 50000);
        newEmployee.raiseSalary(10);

        System.out.println("The expected details: \nName: LVB, moon\nSalary: $55000.0\n--");
        System.out.println("The code result:" + "\nName: " + newEmployee.getName()+ "\nSalary: " + newEmployee.getSalary());
    }
}
