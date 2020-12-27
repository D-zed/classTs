package naixue.two.vip.nefeatures.lambda;

public class Offer {
    private int id; //编号
    private String comName;//公司名称
    private int empNumber;//公司员工总数
    private double salary;//工资


    public Offer(int id, String comName, int empNumber, double salary) {
        this.id = id;
        this.comName = comName;
        this.empNumber = empNumber;
        this.salary = salary;
    }

    public Offer() {
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", comName='" + comName + '\'' +
                ", empNumber=" + empNumber +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public int getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(int empNumber) {
        this.empNumber = empNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Offer(int empNumber) {
        this.empNumber = empNumber;
    }

    public Offer(int id, int empNumber) {
        this.id = id;
        this.empNumber = empNumber;
    }

    private Level level; //优先级

    public Level getLevel() {
        return level;
    }
    public void setLevel(Level level) {
        this.level = level;
    }
    public Offer(int id, String comName, int empNumber, double salary,Level level) {
        this.id = id;
        this.comName = comName;
        this.empNumber = empNumber;
        this.salary = salary;
        this.level = level;
    }
    public enum Level {
        ONE, TWO, THREE;
    }
}
