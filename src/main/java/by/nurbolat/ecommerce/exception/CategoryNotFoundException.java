package by.nurbolat.ecommerce.exception;

import org.springframework.stereotype.Component;

public class CategoryNotFoundException extends Exception{

    @Override
    public String getMessage(){
        return "Category not found!";
    }
}
