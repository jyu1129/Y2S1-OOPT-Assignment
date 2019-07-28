class Branch {
    //Variables
    private static int nextBranchId = 1;
    private String branchId;
    private String branchName;
    private Manager manager;

    //Constructor
    public Branch() {
    }

    public Branch(String branchName, Manager manager) {
        this.branchId = String.format("B%02d",nextBranchId++);
        this.branchName = branchName;
        this.manager = manager;
    }

    //Getter
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

    //Methods
    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", manager=" + manager +
                '}';
    }
}