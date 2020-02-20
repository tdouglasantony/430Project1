/*
CSCI 430 Group 1
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.io.Serializable;
import java.util.*;
import java.io.*;

public class ProductManufacturerList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<ProductManufacturer> productManufacturers = new LinkedList();
    private static ProductManufacturerList productManufacturerList;
    private ProductManufacturerList() {
    }
    public static ProductManufacturerList instance() {
        if (productManufacturerList == null) {
            return (productManufacturerList = new ProductManufacturerList());
        } else {
            return productManufacturerList;
        }
    }

    public void insertProductManufacturer(ProductManufacturer prodMan) {
        productManufacturers.add(prodMan);
    }

    public boolean removeProductManufacturer(ProductManufacturer prodMan) {
        productManufacturers.remove(prodMan);
        return true;
    }

    public Iterator getProductManufacturers(){
        return productManufacturers.iterator();
    }

    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(productManufacturerList);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (productManufacturerList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (productManufacturerList == null) {
                    productManufacturerList = (ProductManufacturerList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    public String toString() {
        return productManufacturers.toString();
    }
}
