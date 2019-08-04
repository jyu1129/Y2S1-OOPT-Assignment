class OrderItem {
    private Product product;
    private static int nextItemNo = 1;
    private static int itemNo;
    private double amount;
    private int quantity = 0;
    private int nextQuantity = 1;

    public OrderItem() {
    }

    public OrderItem(Product product) {
        this.product = product;
        amount = product.getPrice() * quantity;
        itemNo = nextItemNo++;
    }

    //Getter
    public Product getProduct() {
        return product;
    }

    public double getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNextQuantity() {
        return nextQuantity++;
    }

    public static int getNextItemNo() {
        return nextItemNo;
    }

    public static int getItemNo() {
        return itemNo;
    }

    //Setter
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNextQuantity(int nextQuantity) {
        this.nextQuantity = nextQuantity;
    }

    public static void setNextItemNo(int nextItemNo) {
        OrderItem.nextItemNo = nextItemNo;
    }

    public boolean stockOut(int quantity){
        if(product.getStockQuantity()> this.quantity){
            product.setStockQuantity(product.getStockQuantity()-quantity);
            this.quantity++;
            amount = quantity * product.getPrice();
            return true;
        }
        else {
            System.out.println("No quantity left.");
            return false;
        }
    }

    //Methods
    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", nextQuantity=" + nextQuantity +
                '}';
    }

}
