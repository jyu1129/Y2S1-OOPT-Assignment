import java.util.ArrayList;

class TransactionHistory {
    private OrderList[] orderLists;
    private int counter;

    public TransactionHistory(ArrayList<OrderList> multipleOrderLists, int counter) {
        this.orderLists = orderLists;
        this.counter = counter;

        for(int i = 0; i < counter; i++){
            System.out.println(i + 1 + ". RM" + orderLists[i].getTotalAmount());
        }
    }
}
