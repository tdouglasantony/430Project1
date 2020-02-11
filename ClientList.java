import java.util.*;
import java.lang.*;
import java.io.*;
public class ClientList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List clients = new LinkedList();
	private static ClientList ClientList;
	private ClientList(){}
	public static ClientList instance() {
		if (ClientList == null) {
			return (ClientList = new ClientList());
		}
		else {
			return ClientList;
		}
	}
	
	public boolean insertProduct(Product product){
		clients.add(product);
		return true;
	}
	
	public Iterator getclients(){
		return clients.iterator();
	}
}
