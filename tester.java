/*
CSCI 430 Group 1
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.util.concurrent.TimeUnit;

public class tester{

    static int failTests = 0;
    static int passTests = 0;


    public static void main( String args[] ) throws Exception
    {
        System.out.println("Testing all methods for ProductManufacturer with expected and unexpected data. " +
                           "\nThe number of failed test should equal the number of passed tests.");
        runTests1();

        TimeUnit.SECONDS.sleep(4);
        System.out.println("Failed Tests: " + failTests);
        System.out.println("Passed Tests: " + passTests);

        System.out.println("\nTesting all methods for ProductManufacturerList");

        runTests2();

        TimeUnit.SECONDS.sleep(3);


    }

    public static void runTests1(){
        try {
            testProductManufacturerGetPrice();
        }catch (Exception e){ e.printStackTrace();}

        try {
            testProductManufacturerGetProduct();
        }catch (Exception e){ e.printStackTrace();}

        try {
            testProductManufacturerGetManufacturer();
        }catch (Exception e){ e.printStackTrace();}

        try {
            testProductManufacturerSetManufacturer();
        }catch (Exception e){ e.printStackTrace();}

        try {
            testProductManufacturerSetPrice();
        }catch (Exception e){ e.printStackTrace();}

        try {
            testProductManufacturerSetProduct();
        }catch (Exception e){ e.printStackTrace();}


    }

    public static void runTests2(){


            testProductManufacturerListAdd();
            testProductManufacturerListRemove();
            testProductManufacturerListGetProductManufacturers();


    }

    public static void testProductManufacturerGetPrice() throws TestFailException {
        //Create Objects
        Product p = new Product("p1");
        Manufacturer m = new Manufacturer("m1", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m, p, 10.00);

        //Expected
        double expected = 10.00;
        double unexpected = 12.00;

        //Assertion
        if (pm.getPrice() != expected){
            failTests++;
            throw new TestFailException("Price doesn't match");
        }
        else{
            passTests++;
        }

        if (pm.getPrice() != unexpected){
            failTests++;
            throw new TestFailException("Price doesn't match");
        }
        else{
            passTests++;
        }
    }

    public static void testProductManufacturerGetProduct() throws TestFailException {
        //Create Objects
        String productName = "p";

        Product p = new Product(productName);
        Manufacturer m = new Manufacturer("m", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m, p, 10.00);

        //Expected
        //That the product object will match the original
        Product expected = p;
        Product unexpected = new Product("unexpected");


        if(!pm.getProduct().equals(expected)){
            failTests++;
            throw new TestFailException("Product doesn't match");
        }else{
            passTests++;
        }

        if(!pm.getProduct().equals(unexpected)){
            failTests++;
            throw new TestFailException("Product doesn't match");
        }else{
            passTests++;
        }
    }

    public static void testProductManufacturerGetManufacturer() throws TestFailException {
        //Create objects
        String manufacturerName = "m";
        String phoneNumber = "3847983798";

        Product p = new Product("p");
        Manufacturer m = new Manufacturer(manufacturerName, phoneNumber);
        ProductManufacturer pm = new ProductManufacturer(m, p, 10.00);

        //Expected
        //That the manufacturer object will match the original
        Manufacturer expected = m;
        Manufacturer unexpected = new Manufacturer("m", "323");

        if(!pm.getManufacturer().equals(expected)){
            failTests++;
            throw new TestFailException("Manufacturer doesn't match");
        }else{
            passTests++;
        }

        if(!pm.getManufacturer().equals(unexpected)){
            failTests++;
            throw new TestFailException("Manufacturer doesn't match");
        }else{
            passTests++;
        }

    }

    public static void testProductManufacturerSetManufacturer() throws TestFailException {
        //Create Objects
        Product p = new Product("p");
        Manufacturer m1 = new Manufacturer("m1", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m1, p, 10.00);

        //Expected
        //The newly set manufacturer will be returned
        Manufacturer expected = new Manufacturer("m2", "2347983798");
        pm.setMan(expected);
        Manufacturer unexpected = m1;

        if(!pm.getManufacturer().equals(expected)){
            failTests++;
            throw new TestFailException("Manufacturer wasn't set");
        }else{
            passTests++;
        }

        if(!pm.getManufacturer().equals(unexpected)){
            failTests++;
            throw new TestFailException("Manufacturer wasn't set");
        }else{
            passTests++;
        }

    }

    public static void testProductManufacturerSetPrice() throws TestFailException {
        //Create Objects
        Product p = new Product("p");
        Manufacturer m1 = new Manufacturer("m1", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m1, p, 10.00);

        //Expected
        //The newly set price will be returned
        double expected = 10.00;
        double unexpected = 12.00;
        pm.setPrice(expected);


        if(pm.getPrice() != expected){
            failTests++;
            throw new TestFailException("Prices didn't match after the set");
        }else{
            passTests++;
        }

        if(pm.getPrice() != unexpected){
            failTests++;
            throw new TestFailException("Prices didn't match after the set");
        }else{
            passTests++;
        }

    }

    public static void testProductManufacturerSetProduct() throws TestFailException {
        //Create Objects
        Product p = new Product("p");
        Manufacturer m1 = new Manufacturer("m1", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m1, p, 10.00);

        //Expected
        //The newly set price will be returned
        Product expected = new Product("expected");
        pm.setProd(expected);
        Product unexpected = p;


        if(!pm.getProduct().equals(expected)){
            failTests++;
            throw new TestFailException("Products didn't match after the set");
        }else{
            passTests++;
        }

        if(!pm.getProduct().equals(unexpected)){
            failTests++;
            throw new TestFailException("Products didn't match after the set");
        }else{
            passTests++;
        }

    }


    public static void testProductManufacturerListAdd() {
        //Create Objects
        Product p = new Product("p");
        Manufacturer m1 = new Manufacturer("m1", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m1, p, 10.00);
        ProductManufacturerList.instance().insertProductManufacturer(pm);
        System.out.println("ProductManufacturer successfully added to list");

    }

    public static void testProductManufacturerListRemove(){
        //Create Objects
        Product p = new Product("p");
        Manufacturer m1 = new Manufacturer("m1", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m1, p, 10.00);
        ProductManufacturerList.instance().insertProductManufacturer(pm);
        System.out.println(ProductManufacturerList.instance().removeProductManufacturer(pm) + " should be true if it can be removed from the list");
        System.out.println("ProductManufacturer successfully removed from the list");

        ProductManufacturerList.instance().removeProductManufacturer(pm);
        System.out.println("Cannot delete Object not found in list");

    }

    public static void testProductManufacturerListGetProductManufacturers() {
        //Create Objects
        Product p = new Product("p");
        Manufacturer m1 = new Manufacturer("m1", "3847983798");
        ProductManufacturer pm = new ProductManufacturer(m1, p, 10.00);
        ProductManufacturerList.instance().insertProductManufacturer(pm);
        System.out.println(ProductManufacturerList.instance().getProductManufacturers() + " should be an iterator instance of the list");
    }


    static class TestFailException extends Exception {
        public TestFailException(String message){
            super(message);

        }

    }



}


