package by.nurbolat.ecommerce.controller;

import by.nurbolat.ecommerce.db.entity.Category;
import by.nurbolat.ecommerce.db.repository.CategoryRepository;
import by.nurbolat.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(value = "/categories/add")
    public String getAddNewCategory(){
        return "add-category";
    }

    @PostMapping(value = "/categories/add")
    public String addNewCategories(Category category){
        category.setCreatedAt(LocalDateTime.now());
        categoryService.addCategory(category);
        return "redirect:/products";
    }

}
