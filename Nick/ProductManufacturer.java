/*
Group 1
CSCI 430
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.io.Serializable;

public class ProductManufacturer implements Serializable {
    private Manufacturer man;
    private Product prod;
    private Double price;

    public ProductManufacturer(Manufacturer man, Product prod, Double price) {
        this.man = man;
        this.prod = prod;
        this.price = price;
    }

    public Double getPrice() {
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return "manufacturer "+ man + " product " + prod + " price " + price;
    }
}
