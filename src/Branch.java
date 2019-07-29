import java.util.Arrays;
import java.util.StringJoiner;

class Branch {
    //Variables
    private static int branchNo = 0;
    private static int nextBranchId = 1;
    private String branchId;
    private String branchName;
    private Manager manager;
    private Employee[] employees;

    //Constructor
    public Branch() {
    }

    public Branch(String branchName, Manager manager, Employee[] employees) {
        branchNo++;
        this.branchId = String.format("B%02d",nextBranchId++);
        this.branchName = branchName;
        this.manager = manager;
        this.employees = employees;
    }

    //Getter
    public static int getBranchNo() {
        return branchNo;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public Manager getManager() {
        return manager;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    //Setter
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", manager=" + manager +
                ", employees=" + Arrays.toString(employees) +
                '}';
    }
}