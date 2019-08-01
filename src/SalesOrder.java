import java.util.Scanner;

/*2. Implement the salesOrderProcess(), which represent the barcode scanner function that scan the barcode of the
   product and automatically add the product list on the sales order screen and do the calculation of the products
   ordered. The function prompts the user for barcode and compare the barcode with the system's product ID code. Then,
    If the quantity of the product is empty, continue to display the item has no quantity left. Else, if the quantity
    ordered of the specific product is 0, that element of the array will be shift to most left element which is 0 by now.
    Then the most left element will be added by 1 to get ready to be occupied by the other non-repeating product. After
    that, the product quantity will be deducted by 1 and the quantity ordered will be increase by 1. The function will
    display the products ordered details. Finally, the quantity Ordered will be reset for repeating usage.*/
class SalesOrder {

    private ProductItem[] salesOrderDetails;
    private int counter = 0;
    private double total = 0;
    private Scanner scanner = new Scanner(System.in);
    private int mostLeftElement;

    public SalesOrder(ProductItem[] productItems){
        int notNullCounter = 0;

        for(ProductItem prod : productItems){
            if(prod != null){
                notNullCounter++;
            }
        }
        salesOrderDetails = new ProductItem[notNullCounter];

        for(int i = 0; i < salesOrderDetails.length; i++) {
            salesOrderDetails[i] = new ProductItem(productItems[i].getProduct(), productItems[i].getStockQuantity());
        }

        String barcode;
        do {
            System.out.print("\nEnter Barcode(1 to checkout, 2 to edit order list)> ");
            barcode = scanner.nextLine();

            for (int i = 0; i < salesOrderDetails.length; i++) {
                if (salesOrderDetails[i].getProduct().getProductId().equals(barcode)) {
                    if (salesOrderDetails[i].getStockQuantity() > 0) {
                        if(salesOrderDetails[i].getQuantityOrdered() == 0){

                            ProductItem temp = salesOrderDetails[i];
                            salesOrderDetails[i] = salesOrderDetails[mostLeftElement];
                            salesOrderDetails[mostLeftElement] = temp;

                            productItems[i].setStockQuantity(productItems[i].getStockQuantity() - 1);
                            salesOrderDetails[mostLeftElement].setStockQuantity(salesOrderDetails[mostLeftElement].getStockQuantity() - 1);
                            salesOrderDetails[mostLeftElement].setQuantityOrdered(salesOrderDetails[mostLeftElement].getNextQuantityOrdered());

                            mostLeftElement++;
                            counter++;
                        }else {
                            productItems[i].setStockQuantity(productItems[i].getStockQuantity() - 1);
                            salesOrderDetails[i].setStockQuantity(salesOrderDetails[i].getStockQuantity() - 1);
                            salesOrderDetails[i].setQuantityOrdered(salesOrderDetails[i].getNextQuantityOrdered());
                        }

                        receipt();

                        break;
                    }else{
                        System.out.println("No quantity left!");
                        break;
                    }
                } else if (i == salesOrderDetails.length - 1 && !"1".equals(barcode)) {
                    System.out.println("No such barcode.");
                } else if ("2".equals(barcode)){
                    editQuantity();
                    receipt();
                    break;
                }
            }
        } while (!"1".equals(barcode));

        receipt();
    }

    public void receipt(){
        double[] subtotal = new double[256];
        System.out.printf("%-4s%-30s%-9s%-7s%-8s\n\n","No.","Product Name","Quantity","Price","SubTotal");
        for(int i = 0; i < counter; i++) {
            subtotal[i] = salesOrderDetails[i].getProduct().getPrice() * salesOrderDetails[i].getQuantityOrdered();
            System.out.printf("%-4d%-30s%-9d%-7.2f%-8.2f\n", i+1, salesOrderDetails[i].getProduct().getProductName(), salesOrderDetails[i].getQuantityOrdered(), salesOrderDetails[i].getProduct().getPrice(),
                                                             subtotal[i]);
            total += subtotal[i];
        }
        System.out.printf("%-50s%-8.2f\n","Total", total);
        total = 0;
    }

    public void editQuantity(){
        receipt();
        System.out.print("Please select order list number > ");
        int list = scanner.nextInt();
        list--;
        System.out.print("Please enter quantity > ");
        int editedQuantity = scanner.nextInt();
        scanner.nextLine();

        if(editedQuantity != 0) {
            salesOrderDetails[list].setStockQuantity(salesOrderDetails[list].getStockQuantity()-(editedQuantity-salesOrderDetails[list].getQuantityOrdered()));
            salesOrderDetails[list].setQuantityOrdered(editedQuantity);
            salesOrderDetails[list].setNextQuantityOrdered(editedQuantity + 1);
        }else {
            salesOrderDetails[list].setStockQuantity(salesOrderDetails[list].getStockQuantity() + salesOrderDetails[list].getQuantityOrdered());
            salesOrderDetails[list].setQuantityOrdered(editedQuantity);
            salesOrderDetails[list].setNextQuantityOrdered(editedQuantity + 1);
            ProductItem temp = salesOrderDetails[list];
            for(int i = 0; i < salesOrderDetails.length - list - 1; i++) {
                salesOrderDetails[list + i] = salesOrderDetails[list + i + 1];
            }
            salesOrderDetails[salesOrderDetails.length - 1] = temp;
            counter--;
            mostLeftElement--;
        }
    }

    public ProductItem[] getSalesOrderDetails() {
        return salesOrderDetails;
    }

    public int getCounter() {
        return counter;
    }

    public double getTotal(){
        return total;
    }
}
