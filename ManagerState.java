import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerState extends WarehouseState{

	private static Warehouse warehouse;

	private static ManagerState instance;

	private ManagerState(){
		super();
		warehouse = Warehouse.instance();
	}

	public static ManagerState instance(){
		if(instance == null){
			instance = new ManagerState();
		}
		return instance;
	}

	public void process(){
		System.out.println("In ManagerState process.");
	}

	public void run(){
		process();
	}
}
