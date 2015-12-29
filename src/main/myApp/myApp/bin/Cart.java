package myApp.bin;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private ArrayList<CartItem> items = new ArrayList<CartItem>();

    public void add(CartItem item){
        boolean isFound = false;
        int i;
        for(i=0;i<items.size();i++){
            if(items.get(i).getId()==item.getId()){
                isFound=true;
                break;
            }
        }

        if(isFound){
            items.get(i).setAmount(items.get(i).getAmount()+1);
        }
        else
            items.add(item);
    }

    public int getSum(){
        int sum = 0;
        for(CartItem item : items)
        {
            sum+=item.getAmount()*Integer.parseInt(item.getProduct().getCurrentPrice());
        }
        return sum;
    }

    public ArrayList<CartItem> getItems(){
        return items;
    }

    public boolean ifEmpty(){
        return items.isEmpty();
    }

    public void nullify(){
        items=new ArrayList<CartItem>();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
