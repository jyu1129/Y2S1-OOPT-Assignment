class Employee extends Job {
    //Variables
    private static int employeeNo = 0;
    private static int nextEmployeeID = 1;
    private String employeeID;
    private PersonDetails personDetails;
    private String password;

    //Constructor
    public Employee(String jobTitle, PersonDetails personDetails, String password) {
        super(jobTitle);
        employeeNo++;
        this.employeeID = String.format("S%04d", nextEmployeeID++);
        this.personDetails = personDetails;
        this.password = password;
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


    //Setter
    public void setPersonDetails(PersonDetails personDetails){
        this.personDetails = personDetails;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", personDetails=" + personDetails +
                ", password='" + password + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                "} ";
    }
}