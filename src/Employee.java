import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Employee extends PersonDetails implements Serializable {
    //Variables
    private static int nextEmployeeID = 1;
    private String employeeID;
    private String password;
    private Branch branch;

    //Constructor
    public Employee(){}

    public Employee(PersonDetails personDetails, String jobTitle, String password, Branch branch) {
        super(personDetails);
        this.employeeID = String.format("S%04d", nextEmployeeID++);
        this.password = password;
        this.branch = branch;
    }

    //Getter
    public static int getNextEmployeeID() {
        return nextEmployeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPassword() {
        return password;
    }

    public Branch getBranch() {
        return branch;
    }

    //Setter

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", password='" + password + '\'' +
                ", branch=" + branch +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", icNo='" + icNo + '\'' +
                '}';
    }


    public OrderList modifyOrderList(String productCode, ArrayList<OrderItem> orderItem, ArrayList<OrderList> orderLists, int listNo) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < orderItem.size(); i++) {

            //Compare product ID with the database
            if (orderItem.get(i).getProduct().getProdId().equals(productCode)) {
                //To check if the addOrderItem function is successful. orderLists.get(listNo - 1) --> orderLists[listNo - 1]
                if (orderLists.get(listNo).addOrderItem(orderItem.get(i))) {
                    //Show the receipt every items inputted
                    orderLists.get(listNo).receipt(false, 0);
                    break;
                }
                //Only output "No such barcode" until the end element of the array if the product id is not found in the database
            } else if (i == orderItem.size() - 1 && !"1".equals(productCode)) {
                System.out.println("No such barcode.");
                break;
            }
        }

        int editedList;
        int editedQuantity;

        //Edit order list function
        if ("2".equals(productCode)) {
            do {
                System.out.print("Please enter the order list number > ");
                editedList = scanner.nextInt();
                //Validate error
                if (editedList < 1 || editedList > orderLists.get(listNo).getItemCount()) {
                    System.out.println("Invalid order list number!");
                }
            } while (editedList < 1 || editedList > orderLists.get(listNo).getItemCount());

            do {
                System.out.print("Please enter the quantity > ");
                editedQuantity = scanner.nextInt();
                if (editedQuantity < 0) {
                    System.out.println("Invalid quantity!");
                }
            } while (editedQuantity < 0);

            orderLists.get(listNo).editQuantity(editedList, editedQuantity);
            orderLists.get(listNo).receipt(false, 0);
        }
        //Display and gives option to choose product
        if("3".equals(productCode)){
            if (orderLists.get(listNo).addOrderItem(orderItem.get(displayProduct(orderItem)))) {
                //Show the receipt every items inputted
                orderLists.get(listNo).receipt(false, 0);
            }
        }

        return orderLists.get(listNo);
    }

    public void payment(OrderList orderList){
        Scanner scanner = new Scanner(System.in);
        double amount;
        do {
            System.out.print("Please enter the amount of RM you want to pay > ");
            amount = scanner.nextDouble();
            if(amount < orderList.getTotalAmount()){
                System.out.println("The amount of RM you want to pay cannot be lower than the total price.");
            }
        } while (amount < orderList.getTotalAmount());
        System.out.printf("RM%.2f entered.\n", amount);
        orderList.receipt(true, amount);
    }

    public int displayProduct(ArrayList<OrderItem> orderItem){
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%-3s%-11s%-20s%-20s%15s%10s\n","No", "Product ID", "Product Name", "Product Type", "Stock Quantity", "Price");
        for(int i = 0; i < orderItem.size(); i++){
            System.out.printf("%-3d%-11s%-20s%-20s%15d%10.2f\n", i + 1,
                    orderItem.get(i).getProduct().getProdId(),
                    orderItem.get(i).getProduct().getProdName(),
                    orderItem.get(i).getProduct().getProdType(),
                    orderItem.get(i).getProduct().getStockQuantity(),
                    orderItem.get(i).getProduct().getPrice());
        }

        System.out.print("Select a product: ");
        return scanner.nextInt() - 1;
    }

    public void displayTransactionHistory(ArrayList<OrderList> orderLists) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Transaction History");
        System.out.println("----------------------------");
        System.out.printf("%-4s%-10s%10s\n", "No.", "Order ID", "Total (RM)");
        System.out.println("-----------------------");
        for (int i = 0; i < orderLists.size(); i++) {
            System.out.printf("%-4d%-10s%10.2f\n", i + 1, orderLists.get(i).getOrderNo(), orderLists.get(i).getTotalAmount());
        }
        System.out.print("Please select to display details: ");
        int selection = scanner.nextInt();
        orderLists.get(selection - 1).receipt(true, orderLists.get(selection - 1).getAmount());
    }

}