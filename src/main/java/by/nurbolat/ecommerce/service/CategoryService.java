package by.nurbolat.ecommerce.service;

import by.nurbolat.ecommerce.db.entity.Category;
import by.nurbolat.ecommerce.exception.CategoryNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findById(Long id);

    List<Category> findAll() throws CategoryNotFoundException;

    Category addCategory(Category category);

    Category updateCategory(Category category) throws CategoryNotFoundException;

    void deleteCategory(Category category) throws CategoryNotFoundException;
}
