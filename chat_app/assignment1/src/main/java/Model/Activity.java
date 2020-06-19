package Model;

public class Activity {
    private int id;
    private int employeeId;
    private String description;
    private int date;

    public Activity(int employeeId, String description, int date) {
        this.employeeId = employeeId;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
