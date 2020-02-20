/*
CSCI 430 Group 1
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.io.Serializable;

public class LineItem implements Serializable {
    private String productID;
    private int quantityOrdered;
    private int quantityShipped;

    public LineItem() {
        this.productID = "nothing";
        this.quantityOrdered = 0;
        this.quantityShipped = 0;
    }


    public LineItem(String productID, int quantity) {
        this.productID = productID;
        this.quantityOrdered = quantity;
        this.quantityShipped = 0;
    }

    public String getProductID() {
        return productID;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public int getQuantityShipped() {
        return quantityShipped;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public void setQuantityShipped(int quantityShipped) {
        this.quantityShipped = quantityShipped;
    }

    public String toString() {
        return "ProductID "+productID+ " quantityOrdered "+quantityOrdered+ " quantityShipped "+ quantityShipped;
    }
}
