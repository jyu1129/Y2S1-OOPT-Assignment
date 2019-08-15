import java.io.Serializable;

class Manager extends PersonDetails implements Serializable {
    private static int nextManagerID = 1;
    private String managerID;
    private String password;
    private String jobTitle;

    public Manager(PersonDetails personDetails, String password, String jobTitle) {
        super(personDetails);
        this.managerID = String.format("M%04d", nextManagerID);
        this.password = password;
        this.jobTitle = jobTitle;
    }

    //Getter
    public String getManagerID() {
        return managerID;
    }

    public String getPassword() {
        return password;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public static int getNextManagerID() {
        return nextManagerID;
    }

    //Setter
    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerID='" + managerID + '\'' +
                ", password='" + password + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", icNo='" + icNo + '\'' +
                '}';
    }
}
