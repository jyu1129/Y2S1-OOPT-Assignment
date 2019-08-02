public class Product {
    private String prodId;
    private String prodName;
    private String prodType;
    private int stockQuantity;
    private double price;
    private static int nextProdId = 1;

    // Constructors (Must have prodName and price)

    // Constructor with all data available
    public Product(String prodName, String prodType, double price, int stockQuantity) {
        this.prodId = String.format("P%04d", nextProdId++);
        this.prodName = prodName;
        this.prodType = prodType;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    // Constructor without stockQuantity only
    public Product(String prodName, String prodType, double price) {
        this(prodName, prodType, price, 0);
    }

    // Constructor without prodType only
    public Product(String prodName, double price, int stockQuantity) {
        this(prodName, "n/a", price, stockQuantity);

    }

    // Constructor without prodType and stockQuantity
    public Product(String prodName, double price) {
        this(prodName, "n/a", price, 0);
    }

    // Setter

    // Note: Necessary when the database already have some product.
    public static void setNextProdId(int nextProdId) {
        Product.nextProdId = nextProdId;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter
    public String getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdType() {
        return prodType;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    // Methods
    @Override
    public String toString() {
        return String.format(
                "Product: {Product ID: %s\nProduct Name: %s\nProduct Type: %s\nStock Quantity: %d\nPrice: RM %,.2fea\n}", prodId,
                prodName, prodType, stockQuantity, price);
    }

}