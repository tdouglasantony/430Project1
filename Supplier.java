import java.util.*;
import java.lang.*;
import java.io.*;
public class Supplier implements Serializable{
	// Instance variables
	String name;
	
	// Constructor
	public Supplier(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}