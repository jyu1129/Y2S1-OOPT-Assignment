import java.io.Serializable;

class Employee extends PersonDetails implements Serializable {
    //Variables
    private static int nextEmployeeID = 1;
    private String employeeID;
    private String password;
    private Branch branch;

    //Constructor
    public Employee(PersonDetails personDetails, String jobTitle, String password, Branch branch) {
        super(personDetails);
        this.employeeID = String.format("S%04d", nextEmployeeID++);
        this.password = password;
        this.branch = branch;
    }

    //Getter
    public String getEmployeeID() {
        return employeeID;
    }


    public String getPassword() {
        return password;
    }

    public Branch getBranch() {
        return branch;
    }

    //Setter

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
                ", password='" + password + '\'' +
                ", branch=" + branch +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", icNo='" + icNo + '\'' +
                '}';
    }
}