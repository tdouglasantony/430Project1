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

public class UserInterface {
    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1; // 1
    private static final int ADD_PRODUCT = 2; // 1
    private static final int ADD_MANUFACTURER = 3; // 1
    private static final int ADD_PRODUCT_MANUFACTURER = 4; // 2
    private static final int REMOVE_PRODUCT_MANUFACTURER = 5;//2
    private static final int SHOW_MANUFACTURERS_FOR_A_PRODUCT = 6; //
    private static final int SHOW_PRODUCTS_FOR_A_MANUFACTURER = 7; //
    private static final int SHOW_CLIENTS = 8; //
    private static final int SHOW_PRODUCTS = 9;//
    private static final int SHOW_MANUFACTURERS = 10;//
    private static final int SAVE = 11;
    private static final int RETRIEVE = 12;
    private static final int HELP = 13;

    private UserInterface() {
        if (yesOrNo("Look for saved data and  use it?")) {
            retrieve();
        } else {
            warehouse = Warehouse.instance();
        }
    }
    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }
    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }
    private boolean yesOrNo(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }

    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

    public void help() {
        System.out.println("Enter a number between 0 and 13 as explained below:");
        System.out.println(EXIT + " to Exit\n");
        System.out.println(ADD_CLIENT + " to add a client");
        System.out.println(ADD_PRODUCT + " to add a product");
        System.out.println(ADD_MANUFACTURER + " to add a manufacturer");
        System.out.println(ADD_PRODUCT_MANUFACTURER + " to add a manufacturer to a product");
        System.out.println(REMOVE_PRODUCT_MANUFACTURER + " to remove a manufacturer for a product");
        System.out.println(SHOW_MANUFACTURERS_FOR_A_PRODUCT + " to show manufacturers for a particular product");
        System.out.println(SHOW_PRODUCTS_FOR_A_MANUFACTURER + " to show products for a particular manufacturer");
        System.out.println(SHOW_CLIENTS + " to print clients");
        System.out.println(SHOW_PRODUCTS + " to  print products");
        System.out.println(SHOW_MANUFACTURERS + " to  print manufacturers");
        System.out.println(SAVE + " to  save data");
        System.out.println(RETRIEVE + " to  retrieve");
        System.out.println(HELP + " for help");
    }

    public void addClient() {
        String name = getToken("Enter client's name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        Client result;
        result = warehouse.addClient(name, address, phone);
        if (result == null) {
            System.out.println("Could not add client");
        }
        System.out.println(result);
    }

    public void addProduct() {
        Product result;
        do {
            String name = getToken("Enter  name of product");

            result = warehouse.addProduct(name);
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("Product could not be added");
            }
            if (!yesOrNo("Add more products?")) {
                break;
            }
        } while (true);
    }

    public void addManufacturer()   {
        Manufacturer manufacturer;
        String name = getToken("Enter  name of manufacturer");
        String phone = getToken("Enter phone number");

        manufacturer = warehouse.addManufacturer(name, phone);
        if (manufacturer != null) {
            System.out.println(manufacturer);
        }
        else {
            System.out.println("Manufacturer could not be added");
        }
    }

    public void addProductManufacturer()    {
        String id = getToken("Enter manufacturer ID");
        Manufacturer manufacturer = warehouse.searchForManufacturer(id);
        if (manufacturer == null) {
            System.out.println("Manufacturer with ID "+id+" does not exist..");
            return;
        }
        String pid = getToken("Enter product id to which manufacturer is to be added");
        Product product = warehouse.searchForProduct(pid);
        if (product == null) {
            System.out.println("Product with ID "+id+" does not exist..");
            return;
        }
        String price = getToken("Enter manufacturer's price for product");
        double productPrice;
        productPrice = Double.parseDouble(price);
        warehouse.addProductManufacturer(pid, id, productPrice);
    }
    public void removeProductManufacturer() {
        String id = getToken("Enter Product ID");
        Product product = warehouse.searchForProduct(id);
        if (product == null) {
            System.out.println("Could NOT find that product ID...");
        } else {
            Iterator manufacturers = product.getProductManufacturers();

            while (manufacturers.hasNext()){
                ProductManufacturer productmanufacturer = (ProductManufacturer) manufacturers.next();
                System.out.println(""+productmanufacturer);
            }
            String manID = getToken("Enter Manufacturer to remove... ");
            Manufacturer manufacturer = warehouse.searchForManufacturer(manID);
            if (manufacturer == null) {
                System.out.println("That manufacturer does NOT exist in our system.. ");
            } else {
                if (warehouse.removeProductManufacturer(id, manID)) {
                    System.out.println("Manufacturer Successfully removed from product");
                } else {
                    System.out.println("Manufacturer is NOT a supplier for product: "+id);
                }
            }
        }
    }


    public void showProducts() {
        Iterator allProducts = warehouse.getProducts();
        while (allProducts.hasNext()){
            Product product = (Product)(allProducts.next());
            System.out.println(product.getID() + " - " + product.getName());
        }
    }

    public void showManufacturers() {
        Iterator allManufacturers = warehouse.getManufacturers();
        while (allManufacturers.hasNext()){
            Manufacturer manufacturer = (Manufacturer)(allManufacturers.next());
            System.out.println(manufacturer.getID() + " - " + manufacturer.getName());
        }
    }

    public void showClients() {
        Iterator allClients = warehouse.getClients();
        while (allClients.hasNext()){
            Client client = (Client)(allClients.next());
            System.out.println(client.getID() + " - " + client.getName());
        }
    }



    public void showManufacturersForAProduct() {

        String productID = getToken("Enter product ID");
        Product product = warehouse.searchForProduct(productID);

        if (product == null){
            System.out.println("Could not find product with the ID " + productID);
            return;
        }

        Iterator manufacturers = product.getProductManufacturers();

        while (manufacturers.hasNext()){
            ProductManufacturer productManufacturer = (ProductManufacturer) manufacturers.next();
            System.out.println(productManufacturer.getManufacturer() + " price " + productManufacturer.getPrice());
        }
    }

    public void showProductsForAManufacturer() {

        String manID = getToken("Enter manufacturer ID");
        Manufacturer manufacturer = warehouse.searchForManufacturer(manID);

        if (manufacturer == null){
            System.out.println("Could not find manufacturer with the ID " + manID);
            return;
        }

        Iterator products = manufacturer.getProductManufacturers();

        while (products.hasNext()){
            ProductManufacturer productManufacturer = (ProductManufacturer) products.next();
            System.out.println(productManufacturer.getProduct() + " price " + productManufacturer.getPrice());
        }
    }

    private void save() {
        if (Warehouse.save()) {
            System.out.println(" The Warehouse has been successfully saved in the file WarehouseData \n" );
        } else {
            System.out.println(" There has been an error in saving \n" );
        }
    }
    private void retrieve() {
        try {
            Warehouse tempWarehouse = Warehouse.retrieve();
            if (tempWarehouse != null) {
                System.out.println(" The Warehouse has been successfully retrieved from the file WarehouseData \n" );
                warehouse = tempWarehouse;
            } else {
                System.out.println("File doesn't exist; creating new Warehouse" );
                warehouse = Warehouse.instance();
            }
        } catch(Exception cnfe) {
            cnfe.printStackTrace();
        }
    }
    public void process() {
        int command;
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_CLIENT:        addClient();
                    break;
                case ADD_PRODUCT:         addProduct();
                    break;
                case ADD_MANUFACTURER:  addManufacturer();
                    break;
                case ADD_PRODUCT_MANUFACTURER:  addProductManufacturer();
                    break;
                case REMOVE_PRODUCT_MANUFACTURER: removeProductManufacturer();
                    break;
                case SHOW_MANUFACTURERS_FOR_A_PRODUCT: showManufacturersForAProduct();
                    break;
                case SHOW_PRODUCTS_FOR_A_MANUFACTURER: showProductsForAManufacturer();
                    break;
                case SAVE:              save();
                    break;
                case RETRIEVE:          retrieve();
                    break;
                case SHOW_CLIENTS:	showClients();
                    break;
                case SHOW_PRODUCTS:	showProducts();
                    break;
                case SHOW_MANUFACTURERS: showManufacturers();
                    break;
                case HELP:              help();
                    break;
            }
        }
    }
    public static void main(String[] s) {
        UserInterface.instance().process();
    }


}
