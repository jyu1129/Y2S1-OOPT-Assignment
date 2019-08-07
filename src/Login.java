import java.util.Scanner;

/*1. Implement a login() method, which ask the user for username and password and return the employees array element.
 The system will compare the username and password with Employee ID and Password in array of object in Employee class and
 obtain the employees array element by using for loop. If an invalid username or password is entered, continue to prompt
 for valid login details.*/
class Login {
    private String username;
    private String password;

    public Login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login Page");
        System.out.println("----------");

        System.out.print("Username: ");
        username = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();
    }

    public boolean employeeLogin(Employee[] employeesArray) {

        for(int i = 0; i < Employee.getEmployeeNo(); i++ ) {
            if (employeesArray[i].getEmployeeID().equals(username) && employeesArray[i].getPassword().equals(password)) {
                System.out.println("Log in successful.\n");
                System.out.println("Employee ID: " + employeesArray[i].getEmployeeID() + "\nEmployee Name: " + employeesArray[i].getFirstName() + " " + employeesArray[i].getLastName());

                return true;
            }else if (i == Employee.getEmployeeNo() - 1) {
                System.out.println("Wrong username or password.\n");
            }
        }
        return false;
    }


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
}
