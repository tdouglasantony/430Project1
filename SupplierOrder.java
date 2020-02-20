import java.io.Serializable;

public class SupplierOrder implements Serializable {
    private Product product;
    private Manufacturer manufacturer;
    private String orderID;
    private int quantity;
    private boolean received;
    private static final String PRODUCT_STRING = "SO";

    SupplierOrder ( Manufacturer manufacturer, Product product, int quantity) {
        this.manufacturer = manufacturer;
        this.product = product;
        this.orderID = PRODUCT_STRING + (SupplierOrderIdServer.instance()).getID();
        this.quantity = quantity;
        this.received = false;
    }

    public String getID() {return orderID;}
    public Product getProduct() {return product;}
    public boolean getReceived() {return received;}
    public int getQuantity() {return quantity;}
    public void setReceived(boolean received) {this.received = received;}
    public String toString() {
        return "orderID: "+ this.orderID + "supplierID: "+ this.manufacturer.getID() + " productID: " + this.product.getID() + " quantity: "+ this.quantity;
    }
}
