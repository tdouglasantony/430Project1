import java.util.*;
import java.lang.*;
import java.io.*;
public class SupplierList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List suppliers = new LinkedList();
	private static SupplierList SupplierList;
	private SupplierList(){}
	public static SupplierList instance() {
		if (SupplierList == null) {
			return (SupplierList = new SupplierList());
		}
		else {
			return SupplierList;
		}
	}
	
	public boolean insertProduct(Product product){
		suppliers.add(product);
		return true;
	}
	
	public Iterator getsuppliers(){
		return suppliers.iterator();
	}
}
