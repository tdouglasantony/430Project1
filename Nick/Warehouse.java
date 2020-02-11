/*
Group 1
CSCI 430
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    private ClientList clientList;
    private ProductList productList;
    private ManufacturerList manufacturerList;
    private ProductManufacturerList productManufacturerList;


    private static Warehouse warehouse;

    private Warehouse() {
        productList = ProductList.instance();
        clientList = ClientList.instance();
        manufacturerList = ManufacturerList.instance();
        productManufacturerList = ProductManufacturerList.instance();
    }
    public static Warehouse instance() {
        if (warehouse == null) {
            ClientIdServer.instance();
            return (warehouse = new Warehouse());
        } else {
            return warehouse;
        }
    }
    public Product addProduct(String name) {
        Product product = new Product(name);
        if (productList.insertProduct(product)) {
            return (product);
        }
        return null;
    }
    public Manufacturer addManufacturer(String name, String phone)   {
        Manufacturer manufacturer = new Manufacturer(name, phone);
        if (manufacturerList.insertManufacturer(manufacturer))  {
            return (manufacturer);
        }
        return null;
    }
    public Client addClient(String name, String address, String phone) {
        Client client = new Client(name, address, phone);
        if (clientList.insertClient(client)) {
            return (client);
        }
        return null;
    }

    public ProductManufacturer addProductManufacturer (String productID, String manID, Double price) {

        Product product = searchForProduct(productID);
        Manufacturer manufacturer = searchForManufacturer(manID);
        ProductManufacturer prodMan = new ProductManufacturer(manufacturer, product, price);
        product.addManufacturer(prodMan);
        manufacturer.addProduct(prodMan);
        productManufacturerList.insertProductManufacturer(prodMan);
        return prodMan;
    }

    public boolean removeProductManufacturer(String productID, String manID) {
        Product product = searchForProduct(productID);
        Manufacturer manufacturer = searchForManufacturer(manID);
        ProductManufacturer prodMan = searchForProductManufacturer(manID, productID);

        if (product.removeManufacturer(prodMan) && manufacturer.removeProduct(prodMan) && productManufacturerList.removeProductManufacturer(prodMan)) {
            return true;
        }
        return false;
    }


    public Iterator getProducts() {
        return productList.getProducts();
    }

    public Iterator getManufacturers()  { return manufacturerList.getManufacturers(); }

    public Iterator getProductManufacturers() {
        return productManufacturerList.getProductManufacturers();
    }

    public Iterator getClients() {
        return clientList.getClients();
    }


    public Product searchForProduct(String ProductID) {
        Product foundProduct = null;
        Iterator allProducts = getProducts();
        boolean notFound = true;

        while (allProducts.hasNext()) {
            Product temp = (Product)allProducts.next();

            if (temp.getID().equals(ProductID)) {
                notFound = false;
                foundProduct = temp;
                break;
            }
        }

        return foundProduct;
    }

    public Manufacturer searchForManufacturer(String manID) {

        Manufacturer foundManufacturer = null;
        Iterator allManufacturers = getManufacturers();

        while(allManufacturers.hasNext()){
            Manufacturer temp = (Manufacturer)allManufacturers.next();

            if(temp.getID().equals(manID)){
                foundManufacturer = temp;
                break;
            }
        }
        return foundManufacturer; //null if not found
    }

    public ProductManufacturer searchForProductManufacturer(String manID, String productID) {

        ProductManufacturer foundProductManufacturer = null;
        Iterator allProductManufacturers = getProductManufacturers();

        while(allProductManufacturers.hasNext()){
            ProductManufacturer temp = (ProductManufacturer)allProductManufacturers.next();

            if(temp.getManufacturer().getID().equals(manID) && temp.getProduct().getID().equals(productID)){
                foundProductManufacturer = temp;
                break;
            }
        }
        return foundProductManufacturer; //null if not found
    }

    public static Warehouse retrieve() {
        try {
            FileInputStream file = new FileInputStream("WarehouseData");
            ObjectInputStream input = new ObjectInputStream(file);
            input.readObject();
            ClientIdServer.retrieve(input);
            ProductIdServer.retrieve(input);
            ManufacturerIdServer.retrieve(input);
            return warehouse;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }
    public static  boolean save() {
        try {
            FileOutputStream file = new FileOutputStream("WarehouseData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(warehouse);
            output.writeObject(ClientIdServer.instance());
            output.writeObject(ProductIdServer.instance());
            output.writeObject(ManufacturerIdServer.instance());
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(warehouse);
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }
    private void readObject(java.io.ObjectInputStream input) {
        try {
            input.defaultReadObject();
            if (warehouse == null) {
                warehouse = (Warehouse) input.readObject();
            } else {
                input.readObject();
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public String toString() {
        return productList + "\n" + clientList;
    }

}
