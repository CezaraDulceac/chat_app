package Model;

public class Employee {
    private int employeeId;
    private String name;
    private String cnp;
    private int salary;

    public Employee(String name, String cnp, int salary) {
        this.name = name;
        this.cnp = cnp;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", salary=" + salary +
                '}';
    }
}
