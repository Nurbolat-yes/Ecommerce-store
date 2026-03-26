package by.nurbolat.ecommerce.service.impl;

import by.nurbolat.ecommerce.db.entity.Category;
import by.nurbolat.ecommerce.db.repository.CategoryRepository;
import by.nurbolat.ecommerce.exception.CategoryNotFoundException;
import by.nurbolat.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public Category addCategory(Category category) {

        return categoryRepository.save(category);

    }

    @Override
    public Category updateCategory(Category category) throws CategoryNotFoundException{

        var maybeCategory = categoryRepository.findById(category.getId());

        if (maybeCategory.isEmpty()){
            throw new CategoryNotFoundException();
        }

        return categoryRepository.save(category);

    }

    @Override
    public List<Category> getCategories() {

        return categoryRepository.findAll();

    }

    @Override
    public Optional<Category> getCategory(Long id){
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteCategory(Category category) throws CategoryNotFoundException {

        if (categoryRepository.findById(category.getId()).isPresent()){
            categoryRepository.delete(category);
        }else {
            throw new CategoryNotFoundException();
        }

    }
}
