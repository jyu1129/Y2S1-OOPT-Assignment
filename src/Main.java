import java.util.Scanner;

public class Main {
    private static final int max_number = 999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int menuOption;
        int empOrManager;
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
                    Login employeeLogin = new Login(employees);
                    break;
                case 2:
                    Login managerLogin = new Login(managers);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("No such option!");
                    break;
            }
        }while(true);
    }

    /*2. Implement the salesOrderProcess(), which represent the barcode scanner function that scan the barcode of the
    product and automatically add the product list on the sales order screen and do the calculation of the products
    ordered. The function prompts the user for barcode and compare the barcode with the system's product ID code. Then,
     If the quantity of the product is empty, continue to display the item has no quantity left. Else, if the quantity
     ordered of the specific product is 0, that element of the array will be shift to most left element which is 0 by now.
     Then the most left element will be added by 1 to get ready to be occupied by the other non-repeating product. After
     that, the product quantity will be deducted by 1 and the quantity ordered will be increase by 1. The function will
     display the products ordered details. Finally, the quantity Ordered will be reset for repeating usage.*/
    private static void salesOrderProcess(Product[] product) {
        Scanner scanner = new Scanner(System.in);
        int mostLeftElement = 0;
        int counter = 0;
        String barcode;
        do {
            System.out.print("Enter Barcode(1 to continue)> ");
            barcode = scanner.nextLine();

            for (int i = 0; i < Product.getProductNo(); i++) {
                if (product[i].getProductId().equals(barcode)) {
                    if (product[i].getQuantity() > 0) {
                        if(product[i].getQuantityOrdered() == 0){
                            Product temp = product[i];
                            product[i] = product[mostLeftElement];
                            product[mostLeftElement] = temp;
                            i = mostLeftElement++;
                            counter++;
                        }
                        product[i].setQuantity(product[i].getQuantity() - 1);
                        product[i].setQuantityOrdered(product[i].getNextQuantityOrdered());
                        break;
                    }else{
                        System.out.println("No quantity left!");
                        break;
                    }
                } else if (i == Product.getProductNo() - 1 && !"1".equals(barcode)) {
                        System.out.println("No such barcode.");
                    }
            }
        } while (!"1".equals(barcode));

        System.out.printf("%-30s%-9s%-7s%-7s\n\n","Product Name","Quantity","Price","Total");
        for(int i = 0; i < counter; i++) {
            System.out.printf("%-30s%-9d%-7.2f%-7.2f\n", product[i].getProductName(), product[i].getQuantityOrdered(), product[i].getPrice(), product[i].getPrice() * product[i].getQuantityOrdered());
            product[i].setQuantityOrdered(0);
            product[i].setNextQuantityOrdered(1);
        }
    }

    private static void isManager(String isManagerOrNot){
        if("Manager".equals(isManagerOrNot)){
            System.out.println("You are a manager!!!!");
        }else{
            System.out.println("You are not a manager!!! Get out!!!");
        }
    }
}