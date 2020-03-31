import java.util.*;
import java.text.*;
import java.io.*;

public class WarehouseContext{

	private static Warehouse warehouse;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private WarehouseState[] states;

	public String getToken(String prompt){
		do{
			try{
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if(tokenizer.hasMoreTokens()){
					return tokenizer.nextToken();
				}
			}
			catch(IOException ioe){
				System.exit(0);
			}
		}while(true);
	}

	private boolean yesOrNo(String prompt){
		String more = getToken(prompt + "(Y|y)[es] or anything else for no");
		if(more.charAt(0) != 'y' && more.charAt(0) != 'Y'){
			return false;
		}
		return true;
	}

	private void retrieve(){
		try{
			Warehouse tempWarehouse = Warehouse.retrieve();
			if(tempWarehouse != null){
				System.out.println("The warehouse has been successfully retrieved from the file WarehouseData.\n");
				warehouse = tempWarehouse;
			}
			else{
				System.out.println("File does not exist.  Creating new warehouse.\n");
				warehouse = Warehouse.instance();
			}
		}
		catch(Exception cnfe){
			cnfe.printStackTrace();
		}
	}

	private WarehouseContext(){
		System.out.println("In WarehouseContext constructor.");
		if(yesOrNo("Look for saved data and use it?")){
			retrieve();
		}
		else{
			warehouse = Warehouse.instance();
		}
		states = new WarehouseState[4];
	}

	public static void main(String[] args){
		WarehouseContext wc = new WarehouseContext();
	}
}
