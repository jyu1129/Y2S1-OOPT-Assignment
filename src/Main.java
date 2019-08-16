import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Main {
    // Use Array to store username for logout
    private static String[] userName = new String[999];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize variables and objects
        int empOrManager;
        int count = 0;
        boolean loginSuccess;
        String username = null;
        String password = null;
        // Use ArrayList because it is mutable
        ArrayList<PersonDetails> person = new ArrayList<>();
        Manager manager = new Manager();
        Branch branch = new Branch();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Product> product = new ArrayList<>();
        ArrayList<OrderItem> orderItem = new ArrayList<>();
        ArrayList<OrderList> orderLists = new ArrayList<>();

        // Initialize all filepath
        String[] filepath = new String[7];
        filepath[0] = "./obj/person.bin";
        filepath[1] = "./obj/managers.bin";
        filepath[2] = "./obj/branch.bin";
        filepath[3] = "./obj/employees.bin";
        filepath[4] = "./obj/product.bin";
        filepath[5] = "./obj/orderItem.bin";
        filepath[6] = "./obj/orderLists.bin";

        try {
            FileInputStream[] fis = new FileInputStream[filepath.length];
            ObjectInputStream[] ois = new ObjectInputStream[filepath.length];
            for (int i = 0; i < filepath.length; i++) {
                fis[i] = new FileInputStream(filepath[i]);
                ois[i] = new ObjectInputStream(fis[i]);
            }
            person = (ArrayList<PersonDetails>) ois[0].readObject();
            manager = (Manager) ois[1].readObject();
            branch = (Branch) ois[2].readObject();
            employees = (ArrayList<Employee>) ois[3].readObject();
            product = (ArrayList<Product>) ois[4].readObject();
            orderItem = (ArrayList<OrderItem>) ois[5].readObject();
            orderLists = (ArrayList<OrderList>) ois[6].readObject();
            for (int i = 0; i < filepath.length; i++) {
                ois[i].close();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        bootupScreen();

        do {
            System.out.println("Employee or Manager");
            System.out.println("-------------------");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("0. Exit and update files");
            System.out.print("> ");

            try {
                empOrManager = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                empOrManager = -1;
            }

            if (empOrManager != 0) {
                System.out.println("Login Page");
                System.out.println("----------");
                System.out.print("Username: ");
                username = scanner.nextLine();
                count++;

                System.out.print("Password: ");
                password = scanner.nextLine();
            }

            for (int i = 0; i < count; i++) {
                // for(int j = 0; j < count; j++)
                // userName = new String[count];
                userName[i] = username;
            }

            switch (empOrManager) {
            case 1:
                Login empLogin = new Login(username, password);
                loginSuccess = empLogin.employeeLogin(employees);
                if (loginSuccess) {
                    System.out.println("Log in successful.\n");
                    System.out.println("Username: " + empLogin.getUsername() + "\nEmployee Name: "
                            + employees.get(empLogin.getIndex()).getFirstName() + " "
                            + employees.get(empLogin.getIndex()).getLastName());
                    System.out.println(empLogin.getUsername() + " has login at " + empLogin.currentTime() + "\n");
                    employeeMenuOptions(orderItem, orderLists);
                } else {
                    System.out.println("Login failed.");
                }
                break;
            case 2:
                Login mgrLogin = new Login(username, password);
                loginSuccess = mgrLogin.managerLogin(manager);
                if (loginSuccess) {
                    System.out.println("Log in successful.\n");
                    System.out.println("Username: " + mgrLogin.getUsername() + "\nManager Name: "
                            + employees.get(mgrLogin.getIndex()).getFirstName() + " "
                            + employees.get(mgrLogin.getIndex()).getLastName());
                    managerMenuOptions(product, employees, orderLists);
                } else {
                    System.out.println("Login failed.");
                }
                break;
            case 0:
                // Update all data into files
                WriteObjectToFile(person, filepath[0]);
                WriteObjectToFile(manager, filepath[1]);
                WriteObjectToFile(branch, filepath[2]);
                WriteObjectToFile(employees, filepath[3]);
                WriteObjectToFile(product, filepath[4]);
                WriteObjectToFile(orderItem, filepath[5]);
                WriteObjectToFile(orderLists, filepath[6]);
                // Terminates the program
                System.exit(0);
                break;
            default:
                System.out.println("No such option!");
                break;
            }
        } while (true);
    }

    private static void WriteObjectToFile(Object serObj, String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Menu Options for employees
    private static void employeeMenuOptions(ArrayList<OrderItem> orderItem, ArrayList<OrderList> orderLists) {
        Scanner scanner = new Scanner(System.in);
        String productCode;
        Employee employee = new Employee();

        int menuOption;

        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Sales Order");
            System.out.println("2. Transaction History");
            System.out.println("3. Logout");
            System.out.print("> ");

            try {
                menuOption = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                menuOption = -1;
            }
            switch (menuOption) {
            case 1:
                // Adds elements of multipleOrderLists array
                orderLists.add(new OrderList());
                do {
                    do {
                        System.out
                                .print("\nEnter Item ID(1 to checkout, 2 to edit order list, 3 to display products)> ");
                        productCode = scanner.nextLine();

                        // If the item list in order list is empty, it will show this output (Validate
                        // error)
                        if (("1".equals(productCode) || "2".equals(productCode))
                                && orderLists.get(orderLists.size() - 1).getItemCount() == 0) {
                            System.out.println("Please add some items!");
                        }
                    } while (("1".equals(productCode) || "2".equals(productCode))
                            && orderLists.get(orderLists.size() - 1).getItemCount() == 0);

                    // Modify the current element of multipleOrderLists array
                    orderLists.set(orderLists.size() - 1,
                            employee.modifyOrderList(productCode, orderItem, orderLists, orderLists.size() - 1));
                } while (!"1".equals(productCode));

                // After checking out, it goes to payment
                employee.payment(orderLists.get(orderLists.size() - 1));
                break;
            case 2:
                if (orderLists.size() != 0) {
                    employee.displayTransactionHistory(orderLists);
                } else {
                    System.out.println("No Transaction History!");
                }
                break;
            case 3:
                break;
            default:
                System.out.println("No such option!");
                break;
            }
        } while (menuOption != 3);
    }

    private static void managerMenuOptions(ArrayList<Product> products, ArrayList<Employee> employees,
            ArrayList<OrderList> orderLists) {
        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager();

        int menuOption;

        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Add or edit Product Detail");
            System.out.println("2. Add or edit Employee Detail");
            System.out.println("3. Daily Report");
            System.out.println("4. Logout");
            System.out.print("> ");

            try {
                menuOption = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                menuOption = -1;
            }
            switch (menuOption) {
            case 1:
                manager.modifyProduct(products);
                break;
            case 2:
                manager.modifyStaff(employees);
                break;
            case 3:
                manager.dailyReport(orderLists, products);
                break;
            case 4:
                break;
            default:
                System.out.println("No such option!");
                break;
            }
        } while (menuOption != 4);
    }

    private static void bootupScreen() {
        System.out.println(" ____  _____ _____    ____  _____  ____  ____  _____   _ ");
        System.out.println("/  __\\/  __//__ __\\  / ___\\/__ __\\/  _ \\/  __\\/  __/  / \\");
        System.out.println("|  \\/||  \\    / \\    |    \\  / \\  | / \\||  \\/||  \\    | |");
        System.out.println("|  __/|  /_   | |    \\___ |  | |  | \\_/||    /|  /_   | |");
        System.out.println("\\_/   \\____\\  \\_/    \\____/  \\_/  \\____/\\_/\\_\\\\____\\  \\_/");
        System.out.println("Welcome to Pet Store 1 !!!\n");

    }
}