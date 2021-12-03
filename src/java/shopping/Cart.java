/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class Cart implements Serializable {

    private HashMap<String, Book> cart;

    public Cart() {
    }

    public Cart(HashMap<String, Book> cart) {
        this.cart = cart;
    }

    public HashMap<String, Book> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Book> cart) {
        this.cart = cart;
    }

    public void addToCart(Book newBook) {
        if (this.cart == null) {
            cart = new HashMap<>();
        }
        if (this.cart.containsKey(newBook.getBookID())) {
            int currentQuantity = this.cart.get(newBook.getBookID()).getQuantity();
            newBook.setQuantity(currentQuantity + newBook.getQuantity());
        }
        cart.put(newBook.getBookID(), newBook);
    }

    public void updateCart(String id, Book newBook) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.replace(id, newBook);
        }
    }

    public void deleteCart(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }
}
