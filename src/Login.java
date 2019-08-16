import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

/*1. Implement a login() method, which ask the user for username and password and return the employees array element.
 The system will compare the username and password with Employee ID and Password in array of object in Employee class and
 obtain the employees array element by using for loop. If an invalid username or password is entered, continue to prompt
 for valid login details.*/
class Login {
    private String username;
    private String password;
    private int i;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Login time
    public String currentTime() {
        Date now = new Date();

        SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");

        String loginTime = formatTime.format(now);

        return loginTime;
    }

    // Login function for employee
    public boolean employeeLogin(ArrayList<Employee> employeesArray) {

        for (i = 0; i < employeesArray.size(); i++) {

            // Compare username and password with the database
            if (employeesArray.get(i).getEmployeeID().equals(username)
                    && employeesArray.get(i).getPassword().equals(password)) {
                return true;
                // Only output wrong username or password until the end element of the array if
                // the username and password is not found in the database
            }
        }
        return false;
    }

    // Login function for manager
    public boolean managerLogin(Manager manager) {

        if (manager.getManagerID().equals(username) && manager.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public int getIndex() {
        return i;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
