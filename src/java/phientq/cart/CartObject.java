/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String name) {
        //1. check ngăn chứa giỏ có tổn tại k (checking items has existed)
        if (name == null) {
            return;
        }
        if (name.trim().isEmpty()) {
            return;
        }
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //2. Checking item existed
        int quantity = 1;
        if (this.items.containsKey(name)) {
            quantity = this.items.get(name) + 1;
        }
        //3. update items
        this.items.put(name, quantity);
    }
    
    public void RemoveItemFromCart(String name){
        //1. check items has existed
        if (this.items == null) {
            return;
        }
        //2. checking item existed in cart
        if (this.items.containsKey(name)) {
            this.items.remove(name);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
