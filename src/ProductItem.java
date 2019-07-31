class ProductItem {
    private Product product;
    private int stockQuantity;
    private int quantityOrdered = 0;
    private int nextQuantityOrdered = 1;

    public ProductItem() {
    }

    public ProductItem(Product product, int stockQuantity) {
        this.product = product;
        this.stockQuantity = stockQuantity;
    }

    //Getter
    public Product getProduct() {
        return product;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public int getNextQuantityOrdered() {
        return nextQuantityOrdered++;
    }

    //Setter
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public void setNextQuantityOrdered(int nextQuantityOrdered) {
        this.nextQuantityOrdered = nextQuantityOrdered;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "product=" + product +
                ", stockQuantity=" + stockQuantity +
                ", quantityOrdered=" + quantityOrdered +
                ", nextQuantityOrdered=" + nextQuantityOrdered +
                '}';
    }
}
