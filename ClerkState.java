import java.util.*;
import java.text.*;
import java.io.*;

public class ClerkState extends WarehouseState{

	private static Warehouse warehouse;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static ClerkState instance;

	private static final int EXIT = 0;
	private static final int ADD_CLIENT = 1;
	private static final int SHOW_PRODUCTS = 2;
	private static final int SHOW_CLIENTS = 3;
	private static final int SHOW_CLIENTS_WITH_BALANCE = 4;
	private static final int SHOW_WAITLISTED_PRODUCT = 5;
	private static final int MAKE_PAYMENT_FOR_CLIENT = 6;
	private static final int RECIEVE_ORDER = 7;
	private static final int HELP = 8;

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
	
	public void addClient() {
        String name = getToken("Enter client's name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        Client result;
        result = warehouse.addClient(name, address, phone);
        if (result == null) {
            System.out.println("Could not add client");
        }
        else
            System.out.println("Client's ID: " + result.getID());
    }

	private ClerkState(){
		super();
		warehouse = Warehouse.instance();
	}


	public static ClerkState instance(){
		if(instance == null){
			instance = new ClerkState();
		}	
		return instance;
	}

	public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= 22) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

	public void help() {
        System.out.println("Enter a number between 0 and 20 as explained below:");
        System.out.println(EXIT + " to Exit\n");
        System.out.println(ADD_CLIENT + " to add a client");
		System.out.println(SHOW_PRODUCTS + " to print products");
		System.out.println(SHOW_CLIENTS + " to print clients");
		System.out.println(SHOW_CLIENTS_WITH_BALANCE + " to print clients with outstanding balance");
		System.out.println(SHOW_WAITLISTED_PRODUCT + " to print waitlisted products");
		System.out.println(MAKE_PAYMENT_FOR_CLIENT + " to make a payment for a client");
		System.out.println(RECIEVE_ORDER + " to recieve a shipment");
        System.out.println(HELP + " for help");
        
    }

	public void process(){
		int command;
		help();
		while((command = getCommand()) != EXIT){
			switch(command){
				case ADD_CLIENT:	addClient();
					break;
			}
		}
	}

	public void run(){
		process();
	}
}
