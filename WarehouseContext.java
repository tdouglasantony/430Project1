import java.util.*;
import java.text.*;
import java.io.*;

public class WarehouseContext{

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

	private WarehouseContext(){
		System.out.println("In WarehouseContext constructor.");
		if(yesOrNo("Look for saved data and use it?")){
			System.out.println("User typed yes.");
		}
		else{
			System.out.println("User typed no.");
		}
	}

	public static void main(String[] args){
		WarehouseContext wc = new WarehouseContext();
	}
}
