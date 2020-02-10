/* CSCI 430 Project 1
* Test Driver Program
* Spring 2020
* Abhishek Adhikari
* Majed Alsharikh
* Tyler Antony
* Nick Juelich
*/

class TestDriver
{
	public static void main(String args[])
	{
		Client newClient = new Client("Bob Smith");
		Product newProduct = new Product("Pencil", 0.99, 25);
		Supplier newSupplier = new Supplier("Pencil Company");
		ProductList productList = ProductList.instance();
		
		System.out.println("The client's name is: " + newClient.getName() + ".");
		System.out.println("The supplier's name is: " + newSupplier.getName() + ".");
		System.out.println("The product's name is: " + newProduct.getName() + ".");
		System.out.println("The product's price is: " + newProduct.getPrice() + ".");
		System.out.println("The product's quantity is: " + newProduct.getAmount() + ".");
	}
}