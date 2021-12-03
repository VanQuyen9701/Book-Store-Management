/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class Book implements Serializable {

    private String bookID;
    private double price;
    private String name;
    private String catagory;
    private int quantity;
    private String status;

    public Book() {
    }

    public Book(String bookID, double price, String name, String catagory, int quantity, String status) {
        this.bookID = bookID;
        this.price = price;
        this.name = name;
        this.catagory = catagory;
        this.quantity = quantity;
        this.status = status;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
            return true;
        }
        return false;
    }

}
