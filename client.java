/*
CSCI 430 Group 1
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String phone;
    private String id;
    private double balanceDue;
    private static final String CLIENT_STRING = "C";
    private List<Transaction> transactions = new LinkedList();
    private List <Order> waitListOrderIDs = new LinkedList();

    public void addToBalance(Double amount) {
        balanceDue+=amount;
    }


    public  Client (String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        id = CLIENT_STRING + (ClientIdServer.instance()).getId();
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getID() {
        return id;
    }
    public void addWaitListOrderID(Order order){
        waitListOrderIDs.add(order);
    }
    public Iterator getWaitListOrderIDs() {return waitListOrderIDs.iterator();}
    public List<Order> getWaitlist() { return this.waitListOrderIDs;}


    public void setName(String newName) {
        name = newName;
    }
    public void setAddress(String newAddress) {
        address = newAddress;
    }
    public void setPhone(String newPhone) {
        phone = newPhone;
    }

    public String display(){return "ID: " + id + " NAME: " + name + " ADDRESS: " + address + " PHONE: " + phone;}
    public double getBalanceDue() {
        return balanceDue;
    }
    public void subtractFromBalance (double amount) {
        balanceDue -= amount;
    }
    public void addTransaction(Transaction t ){
        transactions.add(t);
    }

}
