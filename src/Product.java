class Product {
    //Variables
    private static int productNo = 0;
    private static int nextProductId = 1;
    private String productId;
    private String productName;
    private String productType;
    private int productWeight;
    private double price;

    //Constructors

    public Product(String productName, String productType, int productWeight, double price) {
        productNo++;
        this.productId = String.format("P%04d",nextProductId++);
        this.productName = productName;
        this.productType = productType;
        this.productWeight = productWeight;
        this.price = price;
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


    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productWeight=" + productWeight +
                ", price=" + price +
                '}';
    }
}