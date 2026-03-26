package by.nurbolat.ecommerce.controller;

import by.nurbolat.ecommerce.db.entity.Product;
import by.nurbolat.ecommerce.exception.CategoryNotFoundException;
import by.nurbolat.ecommerce.exception.ProductNotFoundException;
import by.nurbolat.ecommerce.service.CategoryService;
import by.nurbolat.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping(value = "/products/{id}")
    public String getProductById(@PathVariable Long id,Model model){
        model.addAttribute("product",productService.getProduct(id).get());
        return "card-details";
    }

    @GetMapping(value = "/products")
    public String getProductsByPageable(Model model,
                                        @RequestParam(value = "search", required = false) String search,
                                        @RequestParam(value = "page",required = false, defaultValue = "0") int pageNumber,
                                        @RequestParam(value = "size",required = false, defaultValue = "3") int pageSize,
                                        @RequestParam(value = "sort_by" ,required = false,defaultValue = "price") String sortBy,
                                        @RequestParam(value = "sort_order",required = false,defaultValue = "asc") String sortOrder){


        Page<Product> productPage = productService.getProductsPageWithSortAndSearch(pageNumber,pageSize,sortBy,sortOrder,search);

        model.addAttribute("products",productPage.getContent());

        model.addAttribute("currentPage",productPage.getNumber());
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("totalPages",productPage.getTotalPages());

        model.addAttribute("pageNumbers", productService.generatePageNumbersList(productPage.getTotalPages()));

        model.addAttribute("sortBy",sortBy);
        model.addAttribute("sortOrder",sortOrder);
        model.addAttribute("search",search);

        return "index";
    }

    @GetMapping(value = "products/add")
    public String getPageAddNewProduct(Model model){
        model.addAttribute("categories",categoryService.getCategories());
        return "add-product";
    }

    @PostMapping(value = "products/add")
    public String addNewProduct(Product product){
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping(value = "/products/update/{id}")
    public String getUpdatePage(Model model, @PathVariable Long id) throws CategoryNotFoundException {
        model.addAttribute("product",productService.getProduct(id).get());
        model.addAttribute("categories",categoryService.getCategories());
        return "update-product";
    }

    @PostMapping(value = "/products/update")
    public String updateProduct(Product product) throws ProductNotFoundException {
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @PostMapping(value = "/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
