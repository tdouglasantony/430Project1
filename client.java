class Client{
	// Instance variables
	String name;
	double outstandingBalance;
	
	// Constructor
	public Client(String name)
	{
		this.name = name;
		this.outstandingBalance = 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getOutstandingBalance()
	{
		return outstandingBalance;
	}
	
	public void addToBalance(double num)
	{
		outstandingBalance += num;
	}
}