import java.util.*;
import java.text.*;
import java.io.*;

public class ClerkState extends WarehouseState{

	private static Warehouse warehouse;
	private static ClerkState instance;

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

	public void process(){
		System.out.println("In ClerkState process.\n");
	}

	public void run(){
		process();
	}
}