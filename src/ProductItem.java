class ProductItem {
    private Product product;
    private int quantityOrdered = 0;
    private int nextQuantityOrdered = 1;

    public ProductItem() {
    }

    public ProductItem(Product product, int stockQuantity) {
        this.product = product;
    }

    //Getter
    public Product getProduct() {
        return product;
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


    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public void setNextQuantityOrdered(int nextQuantityOrdered) {
        this.nextQuantityOrdered = nextQuantityOrdered;
    }


    //Methods
    @Override
    public String toString() {
        return "ProductItem{" +
                "product=" + product +
                ", quantityOrdered=" + quantityOrdered +
                ", nextQuantityOrdered=" + nextQuantityOrdered +
                '}';
    }

    public boolean stockOut(int quantity){
        if(product.getStockQuantity()>quantityOrdered){
            product.setStockQuantity(product.getStockQuantity()-quantity);
            return true;
        }
        else return false;
    }
}
