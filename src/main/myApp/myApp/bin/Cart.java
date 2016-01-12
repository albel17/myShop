package myApp.bin;

import myApp.entity.ProductsEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private ArrayList<CartItem> items = new ArrayList<>();

    //Adds new Cart item
    public void add(CartItem item) {
        boolean isFound = false;
        int i;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                isFound = true;
                break;
            }
        }

        if (isFound) {
            items.get(i).setAmount(items.get(i).getAmount() + 1);
        } else
            items.add(item);
    }

    //Removes item from the cart
    public void remove(CartItem item) {
        boolean isFound = false;
        int i;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                isFound = true;
                break;
            }
        }

        if (isFound) {
            if (items.get(i).getAmount() > 1)
                items.get(i).setAmount(items.get(i).getAmount() - 1);
            else
                items.remove(i);
        }
    }

    //Returns true, if cart contains item
    public boolean hasItem(ProductsEntity product) {
        CartItem item = new CartItem(product, 1);
        boolean isFound = false;
        int i;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    //Return cost of all items in the cart
    public int getSum() {
        int sum = 0;
        for (CartItem item : items) {
            sum += item.getAmount() * item.getProduct().getCurrentPrice();
        }
        return sum;
    }

    //returns all items from the cart
    public ArrayList<CartItem> getItems() {
        return items;
    }

    public boolean ifEmpty() {
        return items.isEmpty();
    }

    //delete everything from the cart
    public void nullify() {
        items = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
