package ap.exercises.exe4;

public class Main_EX4_E_3_12 {
    public static void main(String[] args) {
        Employee newEmployee = new Employee("LVB, moon", 50000);
        newEmployee.raiseSalary(10);

        System.out.println("The detsils:" + "\nName: " + newEmployee.getName()+ "\nSalary: " + newEmployee.getSalary());
    }
}
