import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final int max_number = 999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int menuOption;
        int empOrManager;
        boolean loginSuccess = false;
        boolean back = false;

        PersonDetails[] person = new PersonDetails[max_number];
        Manager[] managers = new Manager[max_number];
        Branch branch = new Branch();
        Employee[] employees = new Employee[max_number];
        Product[] products = new Product[max_number];
        ProductItem[] productItem = new ProductItem[max_number];

        person[0] = new PersonDetails("Martin","Gary",'M',"0123456789","mtg@email.com","001230127890");
        managers[0] = new Manager("Manager", person[0], "abc123");
        branch = new Branch("Petaling Jaya", managers[0], employees);

        person[1] = new PersonDetails("Gregor","Clegane",'M',"0123226545","gc1@email.com","000123141331");
        employees[0]= new Employee("Cashier", person[1], "cba321");

        person[2] = new PersonDetails("Jon", "Snow", 'M',"01112806671","js1@gmal.com","901011141221");
        employees[1] = new Employee("Cashier", person[2], "qwe123");

        products[0] = new Product ("Alpo Dog Food","Dog Food", 60.00);
        products[1] = new Product ("Pedigree Dog Food","Dog Food", 50.00);
        products[2] = new Product ("Merrick Dog Food","Dog Food", 90.00);

        productItem[0] = new ProductItem(products[0], 10);
        productItem[1] = new ProductItem(products[1], 20);
        productItem[2] = new ProductItem(products[2], 30);

        do {
            System.out.println("Employee or Manager");
            System.out.println("-------------------");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("0. Exit");
            System.out.print("> ");

            empOrManager = scanner.nextInt();

            switch (empOrManager) {
                case 1:
                    Login empLogin = new Login();
                    loginSuccess = empLogin.employeeLogin(employees);
                    if(loginSuccess) {
                        employeeMenuOptions(productItem);
                        break;
                    }else{
                        break;
                    }
                case 2:
                    Login mgrLogin = new Login();
                    loginSuccess = mgrLogin.managerLogin(managers);
                    if(loginSuccess) {
                        managerMenuOptions();
                        break;
                    }else{
                        break;
                    }
                case 0:
                    System.exit(0);
                default:
                    System.out.println("No such option!");
                    break;
            }
        }while(true);
    }

    public static void employeeMenuOptions(ProductItem[] productItems){
        Scanner scanner = new Scanner(System.in);
        SalesOrder[] salesOrders = new SalesOrder[max_number];
        int counter = 0;

        int menuOption;

        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Sales Order");
            System.out.println("2. Transaction History");
            System.out.println("3. Logout");
            System.out.println("0. Exit");
            System.out.print("> ");

            menuOption = scanner.nextInt();
            switch (menuOption) {
                case 1:
                    salesOrders[counter++] = new SalesOrder(productItems);
                    break;
                case 2:
                    TransactionHistory transactionHistory = new TransactionHistory(salesOrders, counter);
                    break;
                case 3:
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such option!");
                    break;
            }
        } while (menuOption != 2);
    }

    public static void managerMenuOptions(){
        Scanner scanner = new Scanner(System.in);

        int menuOption;

        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Special manager option");
            System.out.println("2. Special manager option");
            System.out.println("3. Logout");
            System.out.println("0. Exit");
            System.out.print("> ");

            menuOption = scanner.nextInt();
            switch (menuOption) {
                case 1:
                    System.out.println("special~");
                    break;
                case 2:
                    System.out.println("special~~~~");
                    break;
                case 3:
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such option!");
                    break;
            }
        } while (menuOption != 3);
    }

}