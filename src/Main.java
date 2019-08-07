import java.util.ArrayList;
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
        Branch branch = new Branch();
        Employee[] employees = new Employee[max_number];
        ArrayList<Product> product = new ArrayList<Product>();
        ArrayList<OrderItem> orderItem = new ArrayList<OrderItem>();
        ArrayList<OrderList> orderLists = new ArrayList<OrderList>();

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

            switch (empOrManager) {
                case 1:
                    Login empLogin = new Login();
                    loginSuccess = empLogin.employeeLogin(employees);
                    if(loginSuccess) {
                        employeeMenuOptions(orderItem, orderLists);
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

    public static void employeeMenuOptions(ArrayList<OrderItem> orderItem, ArrayList<OrderList> multipleOrderLists){
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
                    multipleOrderLists.add(new OrderList());
                    do {
                        System.out.print("\nEnter Item ID(1 to checkout, 2 to edit order list)> ");
                        productCode = scanner.nextLine();
                        multipleOrderLists.set(OrderList.getOrderListNo() - 1, modifyOrderList(productCode, orderItem, multipleOrderLists, OrderList.getOrderListNo()));
                    }while (!"1".equals(productCode));
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
        } while (menuOption != 2);
    }

    public static OrderList modifyOrderList(String productCode, ArrayList<OrderItem> orderItem, ArrayList<OrderList> orderLists, int listNo) {
        Scanner scanner = new Scanner(System.in);
        int list;
        int editedQuantity;

        for (int i = 0; i < orderItem.size(); i++) {

            if (orderItem.get(i).getProduct().getProdId().equals(productCode)) {
                if (orderLists.get(listNo - 1).addOrderItem(orderItem.get(i))) {
                    orderLists.get(listNo - 1).receipt(false, 0);
                    break;
                }
            } else if (i == orderItem.size()-1 && !"1".equals(productCode)) {
                System.out.println("No such barcode.");
                break;
            }
        }
        if ("2".equals(productCode)) {
            do {
                System.out.print("Please enter the order list number > ");
                list = scanner.nextInt();
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



    public static void payment(OrderList orderList){
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