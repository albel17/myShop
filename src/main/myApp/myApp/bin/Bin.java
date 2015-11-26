package myApp.bin;

import java.util.ArrayList;

public class Bin {
    private ArrayList<BinItem> items = new ArrayList<BinItem>();

    public void add(BinItem item){
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

    @Override
    public String toString() {
        return "Bin{" +
                "items=" + items +
                '}';
    }
}
