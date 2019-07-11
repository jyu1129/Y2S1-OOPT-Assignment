public class Staff {
    //Variables
    private static int staffNo = 0;
    private static int nextStaffId = 1;
    private String staffId;
    private String password;
    private String firstName;
    private String lastName;
    private char gender;
    private String phoneNo;
    private String email;
    private String icNo;
    private Jobs job;
    private Branches branch;

    //Constructor
    public Staff(String password, String firstName, String lastName, char gender, String phoneNo, String email, String icNo, Jobs job, Branches branch) {
        staffNo++;
        this.staffId = String.format("S%04d",nextStaffId++);
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.email = email;
        this.icNo = icNo;
        this.job = job;
        this.branch = branch;
    }

    //Getter
    public static int getStaffNo() {
        return staffNo;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public String getIcNo() {
        return icNo;
    }

    public Jobs getJobs() {
        return job;
    }

    public Branches getBranches() {
        return branch;
    }

    //Setter
    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

    //Methods
    public String toString() {
        return  "\nStaff ID: " + staffId +
                "\nName: " + firstName + " " +lastName +
                "\nGender: " + gender +
                "\nPhone No.: " + phoneNo +
                "\nEmail: " + email +
                "\nIC Number: " + icNo +
                job.toString() +
                branch.toString();
    }
}