import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int max_number = 999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menuOption;
        Staff[] staff = new Staff[max_number];
        Product[] product = new Product[max_number];
        Jobs[] job = new Jobs[max_number];

        Branches branch = new Branches("Kuala Lumpur","M001");
        job[0] = new Jobs("Cashier");
        job[1] = new Jobs("Manager");
        staff[0]= new Staff("abc123","Martin","Gary",'M',"0123456789","mtg@email.com","001230127890",job[0],branch);
        staff[1]= new Staff("cba321", "Gregor","Clegane",'M',"0123226545","gc1@email.com","000123141331",job[0],branch);
        staff[2] = new Staff("qwe123", "Jon", "Snow", 'M',"01112806671","js1@gmal.com","901011141221",job[1],branch);
        product[0] = new Product ("Alpo Dog Food","Dog Food", 10,60.00, 10);
        product[1] = new Product ("Pedigree Dog Food","Dog Food", 10,50.00, 10);
        product[2] = new Product ("Merrick Dog Food","Dog Food", 10,90.00, 10);

        //Obtain array element of staff
        int staffArr = login(staff);

        System.out.println("Staff ID: " + staff[staffArr].getStaffId() + "\nStaff Name: " + staff[staffArr].getFirstName() + " " + staff[staffArr].getLastName());
        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Sales Order");
            System.out.println("2. Manager?");
            System.out.println("0. Exit");
            System.out.print("> ");

            menuOption = scanner.nextInt();
            switch (menuOption) {
                case 1:
                    salesOrderProcess(product);
                    break;
                case 2:
                    isManager(staff[staffArr].getJobs().getJobTitle());
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such option!");
                    break;
            }
        }while(true);
    }

    /*1. Implement a login() method, which ask the user for username and password and return the staff array element.
     The system will compare the username and password with Staff ID and Password in array of object in Staff class and
     obtain the staff array element by using for loop. If an invalid username or password is entered, continue to prompt
     for valid login details.*/
    private static int login(Staff[] staff) {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;

        System.out.println("Login Page");
        System.out.println("----------");

        do {
            System.out.print("Username: ");
            username = scanner.nextLine();

            System.out.print("Password: ");
            password = scanner.nextLine();

            for (int i = 0; i < Staff.getStaffNo(); i++) {
                if (staff[i].getStaffId().equals(username) && staff[i].getPassword().equals(password)) {
                    System.out.println("Log in successful.\n");
                    return i;
                } else if (i == Staff.getStaffNo() - 1 ) {
                    System.out.println("Wrong username or password.\n");
                }
            }
        }while (true) ;
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
            System.out.print("Enter Barcode(1 to checkout)> ");
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