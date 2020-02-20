import java.io.Serializable;
import java.util.*;
import java.io.*;

public class SupplierOrderList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SupplierOrder> orders = new LinkedList();
    private static SupplierOrderList supplierOrderList;
    private SupplierOrderList() {
    }
    public static SupplierOrderList instance() {
        if (supplierOrderList == null) {
            return (supplierOrderList = new SupplierOrderList());
        } else {
            return supplierOrderList;
        }
    }

    public boolean insertOrder(SupplierOrder supplierOrder) {
        orders.add(supplierOrder);
        return true;
    }

    public Iterator getSupplierOrders(){
        return orders.iterator();
    }

    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(supplierOrderList);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (supplierOrderList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (supplierOrderList == null) {
                    supplierOrderList = (SupplierOrderList) input.readObject();
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
        return orders.toString();
    }
}
