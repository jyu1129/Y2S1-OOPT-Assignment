import java.util.Scanner;

class SalesOrder {
    private Scanner scanner = new Scanner(System.in);

    public SalesOrder(Product[] products){
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
