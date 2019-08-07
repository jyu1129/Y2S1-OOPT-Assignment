import java.util.Arrays;
import java.util.StringJoiner;

class Branch {
    //Variables
    private static int branchNo = 0;
    private static int nextBranchId = 1;
    private String branchId;
    private String branchName;
    private Manager manager;

    //Constructor
    public Branch() {
    }

    public Branch(String branchName, Manager manager) {
        branchNo++;
        this.branchId = String.format("B%02d",nextBranchId++);
        this.branchName = branchName;
        this.manager = manager;
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

    //Setter
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", manager=" + manager +
                '}';
    }
}