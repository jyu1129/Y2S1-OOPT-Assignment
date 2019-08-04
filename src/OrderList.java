import java.util.ArrayList;
class OrderList {

    private static int orderListNo = 0;
    private String orderNo;
    private static int nextOrderNo = 1;
    private ArrayList<OrderItem> orderItem = new ArrayList<OrderItem>();
    private double totalAmount = 0;
    private int itemCount = 0;

    public OrderList() {
        this.orderNo = String.format("I%06d", nextOrderNo++);
        orderListNo++;
    }

    public boolean addOrderItem(OrderItem item) {
        for (int i = 0; i < itemCount; i++) {
            if (orderItem.get(i).getProduct() == item.getProduct() && orderItem.get(i).stockOut(1)) {
                totalAmount += orderItem.get(i).getAmount();

                OrderItem.setNextItemNo(itemCount + 1);
                return true;
            }
        }

        try{
            orderItem.add(item);
            orderItem.get(itemCount).stockOut(1);
            totalAmount += orderItem.get(itemCount).getAmount();

            itemCount++;
        }catch(Exception e){
            System.out.format("Failed to add item #%s to order #%s.\n", item.getItemNo(), orderNo);
            return false;
        }
        return true;
    }

    public void editQuantity(int list, int quantity){
        list--;
        if(quantity != 0) {
            orderItem.get(list).getProduct().setStockQuantity(orderItem.get(list).getProduct().getStockQuantity() - quantity - orderItem.get(list).getQuantity());
            orderItem.get(list).setQuantity(quantity);
            orderItem.get(list).setNextQuantity(quantity + 1);
        }else {
            orderItem.get(list).getProduct().setStockQuantity(orderItem.get(list).getProduct().getStockQuantity() + orderItem.get(list).getQuantity());
            orderItem.get(list).setQuantity(quantity);
            orderItem.get(list).setNextQuantity(quantity + 1);
            orderItem.remove(list);
        }
    }

    public void receipt(boolean paid, double amount){
        System.out.printf("%-4s%-30s%-9s%-7s%-8s\n\n","No.","Product Name","Quantity","Price","SubTotal");
        for(int i = 0; i < itemCount; i++) {
            System.out.printf("%-4d%-30s%-9d%-7.2f%-8.2f\n", i+1, orderItem.get(i).getProduct().getProdName(), orderItem.get(i).getQuantity(), orderItem.get(i).getProduct().getPrice(),
                                                                  orderItem.get(i).getProduct().getPrice() * orderItem.get(i).getQuantity());
        }
        System.out.printf("%-50s%-8.2f\n","Total", totalAmount);
        if(paid){
            System.out.printf("%-50s%-8.2f\n","Ringgit Malaysia", amount);
            System.out.printf("%-50s%-8.2f\n\n","Change", amount - totalAmount);
        }
    }

    //Setter
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public static void setNextOrderNo(int nextOrderNo) {
        OrderList.nextOrderNo = nextOrderNo;
    }

    public void setOrderItem(ArrayList<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    //Getter
    public static int getOrderListNo() {
        return orderListNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public static int getNextOrderNo() {
        return nextOrderNo;
    }

    public ArrayList<OrderItem> getOrderItem() {
        return orderItem;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getItemCount() {
        return itemCount;
    }
}
