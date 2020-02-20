/*
CSCI 430 Group 1
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.io.Serializable;

public class ProductManufacturer implements Serializable {
    private Manufacturer man;
    private Product prod;
    private double price;

    public ProductManufacturer(Manufacturer man, Product prod, double price) {
        this.man = man;
        this.prod = prod;
        this.price = price;
        this.man.addProduct(this);
        this.prod.addManufacturer(this);
    }

    public double getPrice() {
        return price;
    }
    public Product getProduct() {
        return prod;
    }
    public Manufacturer getManufacturer() {
        return man;
    }
    public void setMan(Manufacturer man) {
        this.man = man;
    }
    public void setProd(Product prod) {
        this.prod = prod;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String toString(){ return man.getID();}

}
