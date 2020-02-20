import java.util.*;
import java.lang.*;
import java.io.*;

public class Manufacturer implements Serializable {

    private String id;
    private String name;
    private String phone;
    private List <ProductManufacturer> products = new LinkedList();
    private static final String MANUFACTURER_STRING = "M";

    Manufacturer (String name, String phone) {
        this.name = name;
        this.phone = phone;
        id = MANUFACTURER_STRING + (ManufacturerIdServer.instance()).getID();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getID() {
        return id;
    }
    public List<ProductManufacturer> getProducts() {return products;}

    public void setId(String id) {this.id = id;}
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addProduct(ProductManufacturer newProd) {
        products.add(newProd);
    }

    public boolean removeProduct(ProductManufacturer prod) {
        products.remove(prod);
        return true;
    }

    public Iterator getProductManufacturers() {
        return products.iterator();
    }

    public String display() {
        return "ID: "+ id + " NAME: "+ name + " PHONE: " + phone;
    }
}
