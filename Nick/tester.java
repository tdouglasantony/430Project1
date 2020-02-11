/*
Group 1
CSCI 430
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

public class tester{


    public static void main( String args[] )
    {

        boolean passed = true;

        try {
            runTests();
        } catch (Exception e){passed = false;}
        if(passed)
            System.out.println("All Tests Passed");
    }
    public static void runTests(){
        try {
            testProductManufacturerGetPrice();
            testProductManufacturerGetProduct();
        }catch (Exception e){e.printStackTrace();}
    }
    public static void testProductManufacturerGetPrice() {
        //Create Objects
        Product p = new Product("p1");
        Manufacturer m = new Manufacturer("m1", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m, p, 10.00);

        //Expected
        double price = 10.00;

        //Assertion
        assert pm.getPrice() == price : "Price doesn't match";
    }
    public static void testProductManufacturerGetProduct() {
        //Create Objects
        String productName = "p";

        Product p = new Product(productName);
        String id = p.getID();
        Manufacturer m = new Manufacturer("m", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m, p, 10.00);

        //Expected
        //That the product object will match the original
        Product expected = new Product(productName);
        expected.setId(id);


        //Assertion
        assert pm.getProduct().equals(expected): "Product doesn't match";
    }

}
