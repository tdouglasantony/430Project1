import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Order implements Serializable {
    private static final String ORDER_STRING = "O";
    private String ID;
    private List<LineItem> lineItems = new LinkedList();
    private String clientID;
    private double totalPrice;
    private boolean processed = false;

    Order ( String clientID) {
        this.clientID = clientID;
        this.totalPrice = 0.0;
        this.processed = false;
        ID = ORDER_STRING + (OrderIdServer.instance()).getID();
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }
    public Iterator<LineItem> getLineItems() { return lineItems.iterator(); }
    public String getClientID() {
        return this.clientID;
    }
    public String getID() {
        return ID;
    }
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setProcessed(boolean b) {
        this.processed = b;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public void setLineItems(List<LineItem> lineItems) {this.lineItems = lineItems;}



    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }




    public LineItem getLineItemByProduct(String productID) {
        LineItem wantedLine = null;
        for (LineItem line : lineItems) {
            if (line.getProductID().equals(productID)) {
                wantedLine = line;
                break;
            }
        }
        return wantedLine;
    }




    public void addLineItem (String productID, int quantity) {
        LineItem newItem = new LineItem();
        newItem.setProductID(productID);
        newItem.setQuantityOrdered(quantity);
        lineItems.add(newItem); 
    }
    public void addDollarAmt(double addedDollars) {
        totalPrice += addedDollars;
    }



    public String toString() {
        return "orderID:" + this.ID + " clientID:" + this.clientID + " price: " + this.totalPrice;
    }
}
