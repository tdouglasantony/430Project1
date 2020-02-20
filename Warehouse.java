
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    private ClientList clientList;
    private ProductList productList;
    private ManufacturerList manufacturerList;
    private ProductManufacturerList productManufacturerList;
    private OrderList orderList;
    private SupplierOrderList supplierOrderList;


    private static Warehouse warehouse;

    private Warehouse() {
        productList = ProductList.instance();
        clientList = ClientList.instance();
        manufacturerList = ManufacturerList.instance();
        productManufacturerList = ProductManufacturerList.instance();
        orderList = OrderList.instance();
        supplierOrderList = SupplierOrderList.instance();
    }
    public static Warehouse instance() {
        if (warehouse == null) {
            //ClientIdServer.instance();
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

    public ProductManufacturer addProductManufacturer (String productID, String manID, double price) {

        Product product = searchForProduct(productID);
        Manufacturer manufacturer = searchForManufacturer(manID);
        ProductManufacturer prodMan = new ProductManufacturer(manufacturer, product, price);
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

    public Client searchForClient(String clientID) {
        Client foundClient = null;
        Iterator allClients = getClients();

        while (allClients.hasNext()) {
            Client temp = (Client)allClients.next();

            if (temp.getID().equals(clientID)) {
                foundClient = temp;
                break;
            }
        }

        return foundClient;
    }

    public Order createOrder(String clientID){

        Order order = new Order(clientID);
        if (orderList.insertOrder(order)){
            return (order);
        }
        return null;
    }

    public SupplierOrder createSupplierOrder(Manufacturer manufacturer, Product product, int quantity){

        SupplierOrder supplierOrder = new SupplierOrder(manufacturer, product, quantity);
        if (supplierOrderList.insertOrder(supplierOrder)){
            return (supplierOrder);
        }
        return null;
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

    public Iterator getOrders() { return  orderList.getOrders();   }

    public Iterator getSupplierOrders() {return supplierOrderList.getSupplierOrders();}



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

    public Order searchForOrder(String orderID){

        Order foundOrder = null;
        Iterator allOrders = warehouse.getOrders();

        while(allOrders.hasNext()){
            Order temp = (Order)allOrders.next();

            if(temp.getID().equals(orderID)){
                foundOrder = temp;
                break;
            }
        }
        return foundOrder; //null if not found
    }

    public SupplierOrder searchForSupplierOrder(String orderID){

        SupplierOrder foundOrder = null;
        SupplierOrder order = null;
        Iterator allOrders = warehouse.getSupplierOrders();

        while (allOrders.hasNext()){
            SupplierOrder temp = (SupplierOrder)allOrders.next();

            if(temp.getID().equals(orderID)){
                foundOrder = temp;
                break;
            }
        }
        return foundOrder; //null if not found
    }

    public ProductManufacturer searchForProductManufacturer(Product product, Manufacturer manufacturer){

        ProductManufacturer productManufacturer = null;
        Iterator allProductManufacturers = getProductManufacturers();

        while (allProductManufacturers.hasNext()){
            ProductManufacturer temp = (ProductManufacturer)allProductManufacturers.next();

            if(temp.getManufacturer().equals(manufacturer) && temp.getProduct().equals(product)){
                productManufacturer = temp;
                break;
            }
        }
        return productManufacturer;
    }


    public void addLineItem(String pID, String orderID, String manufacturerID, int quantity){
        Order foundOrder = null;
        foundOrder = searchForOrder(orderID);
        Product product = null;
        if (foundOrder != null){
            System.out.println("Found order is NOT equal to NULL");
            foundOrder.addLineItem(pID,quantity);
            product = searchForProduct(pID);
            if (product == null) {
                System.out.println("INVALID PRODUCT ID WHEN ADDING TRANSACTION");
                return;
            }

            ProductManufacturer productManufacturer = searchForProductManufacturer(product, searchForManufacturer(manufacturerID) );

            foundOrder.addDollarAmt(productManufacturer.getPrice() * quantity);
        }
        else
            System.out.println("invalid order ID");
    }

    public boolean processOrder(Order order, Manufacturer manufacturer){

        Invoice outgoingInvoice = new Invoice();
        String clientID = order.getClientID();
        Client client = searchForClient(clientID);
        String dateString = new SimpleDateFormat("dd/MM/yy").format(new Date());
        Transaction trans = new Transaction( dateString , "Processed Order", order.getTotalPrice());
        client.addTransaction(trans);


        double priceShipped = 0.0;
        Iterator lineItems = order.getLineItems();

        while (lineItems.hasNext()) {
            LineItem item = (LineItem) lineItems.next();
            String productID = item.getProductID();
            Product product = warehouse.searchForProduct(productID);

            int orderQty = item.getQuantityOrdered();
            int productQty = product.getQuantity();

            //we need to get price here
            ProductManufacturer productManufacturer = searchForProductManufacturer(product, manufacturer);
            double price = productManufacturer.getPrice();


            if (productQty >= orderQty) {
                priceShipped += (orderQty * price);
                item.setQuantityShipped(orderQty);
                product.changeQuantity(orderQty * (-1));
                outgoingInvoice.addLineItem(productID, orderQty);
            } else {
                priceShipped += (productQty * price);
                item.setQuantityShipped(productQty);
                product.addWaitListOrderID(order);
                client.addWaitListOrderID(order);
                product.changeQuantity(productQty * (-1));
                outgoingInvoice.addLineItem(productID, productQty);
            }
        }

        Transaction invoice = new Transaction(dateString, "Invoice", priceShipped);
        outgoingInvoice.setTotalCost(priceShipped);
        client.addTransaction(invoice);
        client.addToBalance(priceShipped);
        order.setProcessed(true);
        InvoiceList.insertInvoice(outgoingInvoice);

        return true;
    }

    public double makePayment(String clientID, double amount){

        Client client = searchForClient(clientID);
        client.subtractFromBalance(amount);
        double balance = client.getBalanceDue();

        String dateString = new SimpleDateFormat("dd/MM/yy").format(new Date());
        Transaction trans = new Transaction( dateString , "Payment received", amount);
        client.addTransaction(trans);

        return balance;
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
        } catch(IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
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
        } catch(IOException e) {
            System.out.println(e.toString());
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

    public boolean receiveAShipment(String orderID){
        int quantityReceived;
        SupplierOrder supplierOrder = searchForSupplierOrder(orderID);
        quantityReceived = supplierOrder.getQuantity();
        Product product = supplierOrder.getProduct();
        System.out.println(supplierOrder.toString());
        Iterator allWaited = product.getWaitListOrderIDs();
        while (allWaited.hasNext() && quantityReceived > 0){
            int waitListOrderQuantity;
            Order order = (Order)allWaited.next();
            LineItem lineItem = order.getLineItemByProduct(product.getID());
            waitListOrderQuantity = lineItem.getQuantityOrdered() - lineItem.getQuantityShipped();
            Invoice invoice = new Invoice();
            if (waitListOrderQuantity <= quantityReceived) {
                invoice.addLineItem(product.getID(), waitListOrderQuantity);
            }
            else {
                invoice.addLineItem(product.getID(), waitListOrderQuantity - quantityReceived);
                quantityReceived = 0;
            }
        }

        return true;

    }

}
