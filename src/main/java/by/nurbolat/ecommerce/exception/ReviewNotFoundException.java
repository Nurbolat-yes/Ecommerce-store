package by.nurbolat.ecommerce.exception;

public class ReviewNotFoundException extends Exception{

    @Override
    public String getMessage(){
        return "Review not found!";
    }
}
