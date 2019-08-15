import java.io.Serializable;

class PersonDetails implements Serializable {
    protected String firstName;
    protected String lastName;
    protected char gender;
    protected String phoneNo;
    protected String email;
    protected String icNo;

    public PersonDetails(){
    }

    public PersonDetails(PersonDetails personDetails) {
        firstName = personDetails.firstName;
        lastName = personDetails.lastName;
        gender = personDetails.gender;
        phoneNo = personDetails.phoneNo;
        email = personDetails.email;
        icNo = personDetails.icNo;
    }

    public PersonDetails(String firstName, String lastName, char gender, String phoneNo, String email, String icNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.email = email;
        this.icNo = icNo;
    }

    //Getter
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

    //Setter
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

    @Override
    public String toString() {
        return "PersonDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", icNo='" + icNo + '\'' +
                '}';
    }
}
