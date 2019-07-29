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

    public SalesOrder(Product[] products){
        Scanner scanner = new Scanner(System.in);
        int mostLeftElement = 0;
        int counter = 0;
        String barcode;
        do {
            System.out.print("Enter Barcode(1 to continue)> ");
            barcode = scanner.nextLine();

            for (int i = 0; i < Product.getProductNo(); i++) {
                if (products[i].getProductId().equals(barcode)) {
                    if (products[i].getQuantity() > 0) {
                        if(products[i].getQuantityOrdered() == 0){
                            Product temp = products[i];
                            products[i] = products[mostLeftElement];
                            products[mostLeftElement] = temp;
                            i = mostLeftElement++;
                            counter++;
                        }
                        products[i].setQuantity(products[i].getQuantity() - 1);
                        products[i].setQuantityOrdered(products[i].getNextQuantityOrdered());
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
            System.out.printf("%-30s%-9d%-7.2f%-7.2f\n", products[i].getProductName(), products[i].getQuantityOrdered(), products[i].getPrice(), products[i].getPrice() * products[i].getQuantityOrdered());
            products[i].setQuantityOrdered(0);
            products[i].setNextQuantityOrdered(1);
        }
    }
}
