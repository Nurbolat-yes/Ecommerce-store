package by.nurbolat.ecommerce.service.impl;

import by.nurbolat.ecommerce.db.entity.Product;
import by.nurbolat.ecommerce.db.repository.ProductRepository;
import by.nurbolat.ecommerce.exception.ProductNotFoundException;
import by.nurbolat.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {

        if (getProduct(product.getId()).isPresent()) {
            product.setCreatedAt(LocalDateTime.now());
            return productRepository.save(product);
        }
        else
            throw new ProductNotFoundException();

    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        if (getProduct(id).isPresent())
            productRepository.deleteById(id);
        else
            throw new ProductNotFoundException();
    }

    @Override
    public Page<Product> getProductsPageWithSortAndSearch(int pageNumber, int pageSize, String sortBy, String sortOrder, String search) {
        PageRequest pageRequest;


        if (Objects.isNull(sortBy) || sortBy.isEmpty() || sortBy.equals("null")){
            pageRequest = PageRequest.of(pageNumber,pageSize);
        }
        else {
            Sort sort = Sort.by(sortOrder.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
            pageRequest = PageRequest.of(pageNumber,pageSize,sort);
        }


        if(Objects.isNull(search) || search.isEmpty()){
            return productRepository.findAll(pageRequest);
        }

        return productRepository.getProductsListBySearchName(pageRequest,search);
    }

    @Override
    public List<Integer> generatePageNumbersList(int totalPage) {
        return IntStream.range(0,totalPage).boxed().toList();
    }

}
