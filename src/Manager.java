class Manager extends Job {
    private static int managerNo = 0;
    private static int nextManagerID = 1;
    private String managerID;
    private PersonDetails personDetails;
    private String password;

    public Manager(String jobTitle, PersonDetails personDetails, String password) {
        super(jobTitle);
        managerNo++;
        this.managerID = String.format("M%04d", nextManagerID);
        this.personDetails = personDetails;
        this.password = password;
    }

    //Getter
    public static int getManagerNo() {
        return managerNo;
    }

    public String getManagerID() {
        return managerID;
    }

    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public String getPassword() {
        return password;
    }

    //Setter
    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerID='" + managerID + '\'' +
                ", personDetails=" + personDetails +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
