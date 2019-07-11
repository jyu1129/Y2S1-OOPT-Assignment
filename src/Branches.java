public class Branches {
    //Variables
    private static int nextBranchId = 1;
    private String branchId;
    private String branchName;
    private String managerId;

    //Constructor
    public Branches(String branchName, String managerId) {
        this.branchId = String.format("B%02d",nextBranchId++);
        this.branchName = branchName;
        this.managerId = managerId;
    }

    //Getter
    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getManagerId() {
        return managerId;
    }

    //Setter
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    //Methods
    public String toString() {
        return  "\nBranch ID: " + branchId +
                "\nBranch Name: " + branchName +
                "\nManager ID: " + managerId;
    }
}