public class Product {
    //Variables
    private static int productNo = 0;
    private static int nextProductId = 1;
    private String productId;
    private String productName;
    private String productType;
    private int productWeight;
    private double price;
    private int stockQuantity;
    private int quantityOrdered;
    private int nextQuantityOrdered = 1;

    //Constructors

    public Product(String productName, String productType, int productWeight, double price, int quantity) {
        productNo++;
        this.productId = String.format("P%04d",nextProductId++);
        this.productName = productName;
        this.productType = productType;
        this.productWeight = productWeight;
        this.price = price;
        this.stockQuantity = quantity;
        this.quantityOrdered = 0;
    }

    //Getter
    public static int getProductNo() {
        return productNo;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public double getPrice() {
        return price;
    }

    public int getProductWeight() {
        return productWeight;
    }

    public int getQuantity() {
        return stockQuantity;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public int getNextQuantityOrdered() {
        return nextQuantityOrdered++;
    }

    //Setter
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductWeight(int productWeight) {
        this.productWeight = productWeight;
    }

    public void setQuantity(int quantity) {
        this.stockQuantity = quantity;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public void setNextQuantityOrdered(int nextQuantityOrdered) {
        this.nextQuantityOrdered = nextQuantityOrdered;
    }

    //Methods
    public String toString() {
        return  "\nProduct ID: " + productId +
                "\nProduct Name: " + productName +
                "\nProduct Type: " + productType +
                "\nProduct Weight: " + productWeight + "kg" +
                "\nPrice: RM" + price +
                "\nQuantity: " + stockQuantity +
                "\nQuantity Ordered: " + quantityOrdered;
    }
}