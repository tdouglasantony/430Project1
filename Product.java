class Product{
	// Instance variables
	String name;
	double price;
	int amount;
	
	// Constructor
	public Product(String name, double price, int amount)
	{
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getOutstandingBalance()
	{
		return price;
	}
	
	public int getAmount()
	{
		return amount;
	}
}