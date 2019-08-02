class TransactionHistory {
    private SalesOrder[] salesOrders;
    private int counter;

    public TransactionHistory(SalesOrder[] salesOrders, int counter) {
        this.salesOrders = salesOrders;
        this.counter = counter;

        for(int i = 0; i < counter; i++){
            System.out.println(i + ". RM" + salesOrders[i].getTotal());
        }
    }
}
