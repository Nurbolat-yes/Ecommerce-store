package by.nurbolat.ecommerce.service;

import by.nurbolat.ecommerce.db.entity.Product;
import by.nurbolat.ecommerce.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> getProduct(Long id);

    List<Product> getProducts();

    Product addProduct(Product product);

    Product updateProduct(Product product) throws ProductNotFoundException;

    void deleteProduct(Long id) throws ProductNotFoundException;

    Page<Product> getProductsPageWithSortAndSearch(int pageNumber, int pageSize, String sortBy, String sortOrder, String search);

    List<Integer> generatePageNumbersList(int totalPage);
}
