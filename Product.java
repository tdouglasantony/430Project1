/*
CSCI 430 Group 1
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
    private static final String PRODUCT_STRING = "P";
    private static final long serialVersionUID = 1L;
    private String name;
    private String id;
    private int quantity;
    private List <Order> waitListOrderIDs = new LinkedList();
    private List <ProductManufacturer> manufacturers = new LinkedList();

    public Product(String name) {
        this.name = name;
        quantity = 0;
        id = PRODUCT_STRING + (ProductIdServer.instance()).getID();
    }

    public String getName() {
        return name;
    }
    public String getID() {
        return id;
    }
    public int getQuantity() {
        return quantity;
    }
    public Iterator getWaitListOrderIDs(){
        return waitListOrderIDs.iterator();
    }
    public List<Order> getWaitlist() { return this.waitListOrderIDs; }
    public List<ProductManufacturer> getManufactureList() { return this.manufacturers;    }


    public void setName(String name) {this.name = name;}
    public void setId(String id) {this.id = id;}
	public void setQuantity(int quantity) {this.quantity = quantity;}

    public void addManufacturer(ProductManufacturer newMan) {
        manufacturers.add(newMan);
    }
    public boolean removeManufacturer(ProductManufacturer man) {
        manufacturers.remove(man);
        return true;
    }

    public Iterator getProductManufacturers() {
        return manufacturers.iterator();
    }
    public String display() {
        return "ID: " + id + " NAME: " + name;
    }
    public void changeQuantity(int amountAdded){
        quantity += amountAdded;
    }
    public void addWaitListOrderID(Order order){
        waitListOrderIDs.add(order);
    }

}
