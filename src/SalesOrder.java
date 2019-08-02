import java.util.Scanner;
class SalesOrder {

    private ProductItem[] salesOrderDetails;
    private int counter = 0;
    private double tempTotal = 0;
    private double total = 0;
    private Scanner scanner = new Scanner(System.in);
    private int mostLeftElement;
    private double amount;

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
            System.out.print("\nEnter Item ID(1 to checkout, 2 to edit order list)> ");
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

                        receipt(false);

                        break;
                    }else{
                        System.out.println("No quantity left!");
                        break;
                    }
                } else if (i == salesOrderDetails.length - 1 && !"1".equals(barcode)) {
                    System.out.println("No such barcode.");
                } else if ("2".equals(barcode)){
                    editQuantity();
                    receipt(false);
                    break;
                }
            }
        } while (!"1".equals(barcode));

        receipt(false);
        for(int i = 0; i < counter; i++) {
            total += salesOrderDetails[i].getProduct().getPrice() * salesOrderDetails[i].getQuantityOrdered();
        }
        payment();
    }

    public void editQuantity(){
        receipt(false);
        System.out.print("Please select order list number > ");
        int list = scanner.nextInt();
        list--;
        System.out.print("Please enter quantity > ");
        int editedQuantity = scanner.nextInt();
        scanner.nextLine();

        if(editedQuantity != 0) {
            salesOrderDetails[list].setStockQuantity(salesOrderDetails[list].getStockQuantity() - editedQuantity - salesOrderDetails[list].getQuantityOrdered());
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

    public void payment(){
        do {
            System.out.print("Please enter the amount of RM you want to pay > ");
            amount = scanner.nextDouble();
            if(amount < total){
                System.out.println("The amount of RM you want to pay cannot be lower than the total price.");
            }
        } while (amount < total);
        System.out.printf("RM%.2f entered.\n", amount);
        receipt(true);
    }

    public void receipt(boolean paid){
        double[] subtotal = new double[256];
        System.out.printf("%-4s%-30s%-9s%-7s%-8s\n\n","No.","Product Name","Quantity","Price","SubTotal");
        for(int i = 0; i < counter; i++) {
            subtotal[i] = salesOrderDetails[i].getProduct().getPrice() * salesOrderDetails[i].getQuantityOrdered();
            System.out.printf("%-4d%-30s%-9d%-7.2f%-8.2f\n", i+1, salesOrderDetails[i].getProduct().getProductName(), salesOrderDetails[i].getQuantityOrdered(), salesOrderDetails[i].getProduct().getPrice(),
                                                             subtotal[i]);
            tempTotal += subtotal[i];
        }
        System.out.printf("%-50s%-8.2f\n","Total", tempTotal);
        if(paid){
            System.out.printf("%-50s%-8.2f\n","Ringgit Malaysia", amount);
            System.out.printf("%-50s%-8.2f\n\n","Change", amount - total);
        }

        tempTotal = 0;
    }

    public ProductItem getSalesOrderDetails(int index) {
        return salesOrderDetails[index];
    }

    public int getCounter() {
        return counter;
    }

    public double getTotal(){
        return total;
    }
}
