package Model;

public class Account {
    private int idNumber;
    private String type;
    private int money;
    private int date;

    public Account(String type, int money, int date) {
        this.type = type;
        this.money = money;
        this.date = date;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idNumber=" + idNumber +
                ", type='" + type + '\'' +
                ", money=" + money +
                ", date=" + date +
                '}';
    }
}
