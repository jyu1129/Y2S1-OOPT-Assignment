import java.util.Scanner;

/*1. Implement a login() method, which ask the user for username and password and return the employees array element.
 The system will compare the username and password with Employee ID and Password in array of object in Employee class and
 obtain the employees array element by using for loop. If an invalid username or password is entered, continue to prompt
 for valid login details.*/
class Login {
    private Scanner scanner = new Scanner(System.in);
    private MenuOptions menuOptions = new MenuOptions();

    public Login(Employee[] employeesArray) {
        Employee[] employees = new Employee[Employee.getEmployeeNo()];
        System.arraycopy(employeesArray, 0, employees, 0, Employee.getEmployeeNo());

        String username;
        String password;
        int i;
        boolean back = false;

        System.out.println("Login Page");
        System.out.println("----------");

        do {
            System.out.print("Username: ");
            username = scanner.nextLine();

            System.out.print("Password: ");
            password = scanner.nextLine();

            for (i = 0; i < Employee.getEmployeeNo(); i++) {
                if (employees[i].getEmployeeID().equals(username) && employees[i].getPassword().equals(password)) {
                    System.out.println("Log in successful.\n");
                    PersonDetails employeeDetails = employees[i].getPersonDetails();
                    System.out.println("Employee ID: " + employees[i].getEmployeeID() + "\nEmployee Name: " + employeeDetails.getFirstName() + " " + employeeDetails.getLastName());

                    back = menuOptions.employeeMenuOptions();
                    break;
                } else if (i == Employee.getEmployeeNo() - 1) {
                    System.out.println("Wrong username or password.\n");
                }
            }
        }while(!back);
    }


    public Login(Manager[] managersArray){
        Manager[] managers = new Manager[Manager.getManagerNo()];
        System.arraycopy(managersArray, 0, managers, 0, Manager.getManagerNo());

        String username;
        String password;
        int i;
        boolean back = false;

        System.out.println("Login Page");
        System.out.println("----------");

        do {
            System.out.print("Username: ");
            username = scanner.nextLine();

            System.out.print("Password: ");
            password = scanner.nextLine();

            for (i = 0; i < Manager.getManagerNo(); i++) {
                if (managers[i].getManagerID().equals(username) && managers[i].getPassword().equals(password)) {
                    System.out.println("Log in successful.\n");
                    PersonDetails managerDetails = managers[i].getPersonDetails();
                    System.out.println("Manager ID: " + managers[i].getManagerID() + "\nManager Name: " + managerDetails.getFirstName() + " " + managerDetails.getLastName());

                    back = menuOptions.managerMenuOptions();
                    break;
                } else if (i == Manager.getManagerNo() - 1) {
                    System.out.println("Wrong username or password.\n");
                }
            }
        }while(!back);
    }
}
