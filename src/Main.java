import java.util.Scanner;

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
        Branch[] branches = new Branch[max_number];
        Employee[] employees = new Employee[max_number];
        Product[] products = new Product[max_number];

        person[0] = new PersonDetails("Martin","Gary",'M',"0123456789","mtg@email.com","001230127890");
        managers[0] = new Manager("Manager", person[0], "abc123");
        branches[0] = new Branch("Petaling Jaya", managers[0]);

        person[1] = new PersonDetails("Gregor","Clegane",'M',"0123226545","gc1@email.com","000123141331");
        employees[0]= new Employee("Cashier", person[1], "cba321", branches[0]);

        person[2] = new PersonDetails("Jon", "Snow", 'M',"01112806671","js1@gmal.com","901011141221");
        employees[1] = new Employee("Cashier", person[2], "qwe123", branches[0]);

        products[0] = new Product ("Alpo Dog Food","Dog Food", 10,60.00, 10);
        products[1] = new Product ("Pedigree Dog Food","Dog Food", 10,50.00, 10);
        products[2] = new Product ("Merrick Dog Food","Dog Food", 10,90.00, 10);

        MenuOptions menu = new MenuOptions();

        do {
            System.out.println("Employee or Manager");
            System.out.println("-------------------");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("0. Exit");
            System.out.print("> ");

            empOrManager = scanner.nextInt();
            Login login = new Login();

            switch (empOrManager) {
                case 1:
                    loginSuccess = login.employeeLogin(employees);
                    if(loginSuccess) {
                        menu.employeeMenuOptions(products);
                        break;
                    }else{
                        break;
                    }
                case 2:
                    loginSuccess = login.managerLogin(managers);
                    if(loginSuccess) {
                        menu.managerMenuOptions();
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

}