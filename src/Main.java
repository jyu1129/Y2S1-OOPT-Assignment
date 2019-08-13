import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    //Define a max number for our system
    private static final int max_number = 999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int empOrManager;
        boolean loginSuccess;
        String username = null;
        String password = null;

        PersonDetails[] person = new PersonDetails[max_number];
        Manager[] managers = new Manager[max_number];
        Branch branch;
        Employee[] employees = new Employee[max_number];
        //Use ArrayList because it is mutable
        ArrayList<Product> product = new ArrayList<>();
        ArrayList<OrderItem> orderItem = new ArrayList<>();
        ArrayList<OrderList> orderLists = new ArrayList<>();

        person[0] = new PersonDetails("Martin","Gary",'M',"0123456789","mtg@email.com","001230127890");
        managers[0] = new Manager(person[0],"abc123", "Manager");
        branch = new Branch("Petaling Jaya", managers[0]);

        person[1] = new PersonDetails("Gregor","Clegane",'M',"0123226545","gc1@email.com","000123141331");
        employees[0]= new Employee(person[1],"Cashier", "cba321", branch);

        person[2] = new PersonDetails("Jon", "Snow", 'M',"01112806671","js1@gmal.com","901011141221");
        employees[1] = new Employee(person[2],"Cashier","qwe123", branch);

        product.add(new Product ("Alpo Dog Food","Dog Food", 60.00, 30));
        product.add(new Product ("Pedigree Dog Food","Dog Food", 50.00, 20));
        product.add(new Product ("Merrick Dog Food","Dog Food", 90.00, 50));

        orderItem.add(new OrderItem(product.get(0)));
        orderItem.add(new OrderItem(product.get(1)));
        orderItem.add(new OrderItem(product.get(2)));

        do {
            System.out.println("Employee or Manager");
            System.out.println("-------------------");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("0. Exit");
            System.out.print("> ");

            empOrManager = scanner.nextInt();
            scanner.nextLine();

            if(empOrManager != 0) {
                System.out.println("Login Page");
                System.out.println("----------");
                System.out.print("Username: ");
                username = scanner.nextLine();

                System.out.print("Password: ");
                password = scanner.nextLine();
            }

            switch (empOrManager) {
                case 1:
                    Login empLogin = new Login(username,password);
                    loginSuccess = empLogin.employeeLogin(employees);
                    if(loginSuccess) {
                        System.out.println("Log in successful.\n");
                        System.out.println("Username: " + empLogin.getUsername() + "\nEmployee Name: " + employees[empLogin.getIndex()].getFirstName() + " " + employees[empLogin.getIndex()].getLastName());
                        employeeMenuOptions(orderItem, orderLists);
                        break;
                    }else{
                        System.out.println("Login failed.");
                        break;
                    }
                case 2:
                    Login mgrLogin = new Login(username,password);
                    loginSuccess = mgrLogin.managerLogin(managers);
                    if(loginSuccess) {
                        System.out.println("Log in successful.\n");
                        System.out.println("Username: " + mgrLogin.getUsername() + "\nEmployee Name: " + employees[mgrLogin.getIndex()].getFirstName() + " " + employees[mgrLogin.getIndex()].getLastName());
                        managerMenuOptions();
                        break;
                    }else{
                        System.out.println("Login failed.");
                        break;
                    }
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such option!");
                    break;
            }
        }while(true);
    }

    //Menu Options for employees
    private static void employeeMenuOptions(ArrayList<OrderItem> orderItem, ArrayList<OrderList> multipleOrderLists){
        Scanner scanner = new Scanner(System.in);
        String productCode;

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
            scanner.nextLine();
            switch (menuOption) {
                case 1:
                    //Adds elements of multipleOrderLists array
                    multipleOrderLists.add(new OrderList());
                    do {
                        do {
                            System.out.print("\nEnter Item ID(1 to checkout, 2 to edit order list)> ");
                            productCode = scanner.nextLine();
                            //If the item list in order list is empty, it will show this output (Validate error)
                            if (("1".equals(productCode) || "2".equals(productCode)) && OrderList.getItemCount() == 0) {
                                System.out.println("Please add some items!");
                            }
                        }while(("1".equals(productCode) || "2".equals(productCode)) && OrderList.getItemCount() == 0);
                        //Modify the current element of multipleOrderLists array
                        multipleOrderLists.set(OrderList.getOrderListNo() - 1, modifyOrderList(productCode, orderItem, multipleOrderLists, OrderList.getOrderListNo()));
                    }while (!"1".equals(productCode));
                    //After checking out, it goes to payment
                    payment(multipleOrderLists.get(OrderList.getOrderListNo() - 1));
                    break;
                case 2:
                    TransactionHistory transactionHistory = new TransactionHistory(multipleOrderLists, OrderList.getOrderListNo());
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

    private static OrderList modifyOrderList(String productCode, ArrayList<OrderItem> orderItem, ArrayList<OrderList> orderLists, int listNo) {
        Scanner scanner = new Scanner(System.in);
        int list;
        int editedQuantity;

        for (int i = 0; i < orderItem.size(); i++) {

            //Compare product ID with the database
            if (orderItem.get(i).getProduct().getProdId().equals(productCode)) {
                //To check if the addOrderItem function is successful. orderLists.get(listNo - 1) --> orderLists[listNo - 1]
                if (orderLists.get(listNo - 1).addOrderItem(orderItem.get(i))) {
                    //Show the receipt every items inputted
                    orderLists.get(listNo - 1).receipt(false, 0);
                    break;
                }
                //Only output "No such barcode" until the end element of the array if the product id is not found in the database
            } else if (i == orderItem.size()-1 && !"1".equals(productCode)) {
                System.out.println("No such barcode.");
                break;
            }
        }
        //Edit order list function
        if ("2".equals(productCode)) {
            do {
                System.out.print("Please enter the order list number > ");
                list = scanner.nextInt();
                //Validate error
                if (list < 1 || list > OrderList.getItemCount()) {
                    System.out.println("Invalid order list number!");
                }
            } while (list < 1 || list > OrderList.getItemCount());

            do {
                System.out.print("Please enter the quantity > ");
                editedQuantity = scanner.nextInt();
                if (editedQuantity < 0) {
                    System.out.println("Invalid quantity!");
                }
            } while (editedQuantity < 0);

            orderLists.get(listNo - 1).editQuantity(list, editedQuantity);
            orderLists.get(listNo - 1).receipt(false, 0);
        }

        return orderLists.get(listNo - 1);
    }



    private static void payment(OrderList orderList){
        Scanner scanner = new Scanner(System.in);
        double amount;
            do {
                System.out.print("Please enter the amount of RM you want to pay > ");
                amount = scanner.nextDouble();
                if(amount < orderList.getTotalAmount()){
                    System.out.println("The amount of RM you want to pay cannot be lower than the totalAmount price.");
                }
            } while (amount < orderList.getTotalAmount());
            System.out.printf("RM%.2f entered.\n", amount);
            orderList.receipt(true, amount);
    }

    private static void managerMenuOptions(){
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