import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Invoice implements Serializable {
    private double totalCost;
    private List<LineItem> lineItems = new LinkedList();

    Invoice(){
        this.totalCost = 0.0;
    }

    public double getTotalCost() { return totalCost; }
    public Iterator<LineItem> getLineItems() {
        return lineItems.iterator();
    }

    public void setTotalCost(double totalCost){ this.totalCost = totalCost; }
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void addLineItem (String productID, int quantity) {
        LineItem newItem = new LineItem(productID, quantity);
        lineItems.add(newItem);
    }

    public String toString() { return " price " + this.totalCost; }
}