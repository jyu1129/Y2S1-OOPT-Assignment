class Product {
    //Variables
    private static int nextProductId = 1;
    private String productId;
    private String productName;
    private String productType;
    private double price;

    //Constructors

    public Product(String productName, String productType, double price) {
        this.productId = String.format("P%04d",nextProductId++);
        this.productName = productName;
        this.productType = productType;
        this.price = price;
    }

    //Getter
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


    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", price=" + price +
                '}';
    }
}