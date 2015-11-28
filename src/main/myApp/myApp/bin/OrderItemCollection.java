package myApp.bin;

import myApp.DAO.ProductsDAO;

import java.util.ArrayList;

public class OrderItemCollection {
    private ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();

    public OrderItemCollection(Bin bin) {
        ProductsDAO dao = new ProductsDAO();
        for(BinItem binItem : bin.getItems()){
            OrderItem item = new OrderItem(dao.getProductByID(binItem.getId()), binItem.getAmount());
            orderItems.add(item);
        }
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getName(int id){
        return orderItems.get(id).getName();
    }

    public int getAmount(int id){
        return orderItems.get(id).getAmount();
    }
}
