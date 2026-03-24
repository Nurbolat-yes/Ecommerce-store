package by.nurbolat.ecommerce.controller;

import by.nurbolat.ecommerce.db.entity.Category;
import by.nurbolat.ecommerce.db.entity.Product;
import by.nurbolat.ecommerce.db.repository.CategoryRepository;
import by.nurbolat.ecommerce.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping(value = "/products/{id}")
    public String getProductById(@PathVariable Long id,Model model){
        model.addAttribute("product",productRepository.findById(id).get());
        return "card-details";
    }

    @GetMapping(value = "/products")
    public String getProductsByPageable(Model model,
                                        @RequestParam(value = "search", required = false) String search,
                                        @RequestParam(value = "page",required = false, defaultValue = "0") int pageNumber,
                                        @RequestParam(value = "size",required = false, defaultValue = "3") int pageSize,
                                        @RequestParam(value = "sort_by" ,required = false,defaultValue = "price") String sortBy,
                                        @RequestParam(value = "sort_order",required = false,defaultValue = "asc") String sortOrder){

        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,sortBy);

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> productPage;

        if (search == null){
            productPage = productRepository.findAll(pageRequest);
        }else {
            productPage = productRepository.getProductsListBySearchName(pageRequest,search);
            model.addAttribute("search",search);
        }

        model.addAttribute("currentPage",productPage.getNumber());
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("totalPages",productPage.getTotalPages());

        model.addAttribute("pageNumbers", IntStream.range(0,productPage.getTotalPages()).boxed().toList());

        model.addAttribute("sortBy",sortBy);
        model.addAttribute("sortOrder",sortOrder);

        model.addAttribute("products",productPage.getContent());

        return "index";
    }

    @GetMapping(value = "products/add")
    public String getPageAddNewProduct(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories",categories);
        return "add-product";
    }

    @PostMapping(value = "products/add")
    public String addNewProduct(Product product){
        productRepository.save(product);
        return "redirect:/products";
    }

}
