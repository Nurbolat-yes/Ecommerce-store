package by.nurbolat.ecommerce.exception;

public class ProductNotFoundException extends Exception{

    @Override
    public String getMessage(){
        return "Product not found!";
    }
}
