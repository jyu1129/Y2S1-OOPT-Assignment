class Employee extends Job {
    //Variables
    private static int employeeNo = 0;
    private static int nextEmployeeID = 1;
    private String employeeID;
    private PersonDetails personDetails;
    private String password;
    private Branch branch;

    //Constructor
    public Employee(String jobTitle, PersonDetails personDetails, String password, Branch branch) {
        super(jobTitle);
        employeeNo++;
        this.employeeID = String.format("S%04d", nextEmployeeID++);
        this.personDetails = personDetails;
        this.password = password;
        this.branch = branch;
    }

    //Getter
    public static int getEmployeeNo() {
        return employeeNo;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public PersonDetails getPersonDetails(){
        return personDetails;
    }

    public String getPassword() {
        return password;
    }

    public Branch getBranches() {
        return branch;
    }

    //Setter
    public void setPersonDetails(PersonDetails personDetails){
        this.personDetails = personDetails;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", personDetails=" + personDetails +
                ", password='" + password + '\'' +
                ", branch=" + branch +
                ", jobTitle='" + jobTitle + '\'' +
                "} ";
    }
}