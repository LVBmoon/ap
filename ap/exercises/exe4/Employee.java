package ap.exercises.exe4;

public class Employee {
    private String employeeName;
    private double salary;

    public Employee(String employeeName, double currentSalary){
        if (employeeName.isEmpty()){
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        this.employeeName = employeeName;
        this.salary = currentSalary;
    }

    public String getName() {
        return employeeName;
    }

    public double getSalary(){
        return salary;
    }

    public void raiseSalary(double byPercent) {
        if (byPercent > 0) {
            salary += (salary * byPercent / 100);
        }
        else {
            throw new IllegalArgumentException("Salary must be positive!");
        }
    }

//    public void setEmployeeName(String employeeName) {
//        if (!employeeName.isEmpty()) {
//            this.employeeName = employeeName;
//        }
//        else {
//            throw new IllegalArgumentException("Employee name cannot be empty");
//        }
//    }
}
