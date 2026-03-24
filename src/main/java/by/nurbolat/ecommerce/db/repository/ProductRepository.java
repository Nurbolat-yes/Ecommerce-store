package by.nurbolat.ecommerce.db.repository;

import by.nurbolat.ecommerce.db.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{


    @Query("SELECT p FROM Product p WHERE p.name ILIKE concat('%',:search,'%')")
    Page<Product> getProductsListBySearchName(Pageable pageable, String search);

    @Query("SELECT p FROM Product p WHERE p.category.name=:categoryName")
    Page<Product> getProductsByCategoryId(Pageable pageable,String categoryName);
}
