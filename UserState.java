import java.util.*;
import java.text.*;
import java.io.*;

public class UserState extends WarehouseState{
	
	private static Warehouse warehouse;
	private static UserState instance;

	private UserState(){
		super();
		warehouse = Warehouse.instance();
	}

	public static UserState instance(){
		if(instance == null){
			instance = new UserState();			
		}
		return instance;
	}

	public void process(){
		System.out.println("In UserState process.\n");
	}

	public void run(){
		process();
	}
}
