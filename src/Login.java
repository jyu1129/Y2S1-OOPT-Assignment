import java.util.Scanner;

/*1. Implement a login() method, which ask the user for username and password and return the employees array element.
 The system will compare the username and password with Employee ID and Password in array of object in Employee class and
 obtain the employees array element by using for loop. If an invalid username or password is entered, continue to prompt
 for valid login details.*/
class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Login function for employee
    public boolean employeeLogin(Employee[] employeesArray) {

        for(int i = 0; i < Employee.getEmployeeNo(); i++ ) {
            //Compare username and password with the database
            if (employeesArray[i].getEmployeeID().equals(username) && employeesArray[i].getPassword().equals(password)) {
                System.out.println("Log in successful.\n");
                System.out.println("Employee ID: " + employeesArray[i].getEmployeeID() + "\nEmployee Name: " + employeesArray[i].getFirstName() + " " + employeesArray[i].getLastName());

                return true;
            //Only output wrong username or password until the end element of the array if the username and password is not found in the database
            }else if (i == Employee.getEmployeeNo() - 1) {
                System.out.println("Wrong username or password.\n");
            }
        }
        return false;
    }

    //Login function for manager
    public boolean managerLogin(Manager[] managersArray){

        for (int i = 0; i < Manager.getManagerNo(); i++) {
            if (managersArray[i].getManagerID().equals(username) && managersArray[i].getPassword().equals(password)) {
                System.out.println("Log in successful.\n");
                System.out.println("Manager ID: " + managersArray[i].getManagerID() + "\nManager Name: " + managersArray[i].getFirstName() + " " + managersArray[i].getLastName());

                return true;
            } else if (i == Manager.getManagerNo() - 1) {
                System.out.println("Wrong username or password.\n");
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
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
