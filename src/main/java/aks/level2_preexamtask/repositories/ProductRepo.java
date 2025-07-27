package aks.level2_preexamtask.repositories;

import aks.level2_preexamtask.dto.productDto.ProductResponseForGetById;
import aks.level2_preexamtask.entities.Product;

import aks.level2_preexamtask.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("""
                SELECT p FROM Product p
                WHERE (:categoriesSize=0 OR p.category IN :categories)
                  AND (:minPrice IS NULL OR p.price >= :minPrice)
                  AND (:maxPrice IS NULL OR p.price <= :maxPrice)
            """)
    Page<Product> findAllWithFilters(
            @Param("categories") List<Category> categories,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            Integer categoriesSize,
            Pageable pageable
    );


//    @Query("""
//                SELECT p FROM Product p
//                LEFT JOIN FETCH p.comments
//
//                WHERE p.id = :productID
//            """)
//    Product findByIdWithComment(Long productID);
//    @Query("""
//                SELECT p FROM Product p
//                LEFT JOIN FETCH p.images
//                WHERE p.id = :productID
//            """)
//    Product findByIdWithImages(Long id);
//    @Query("""
//                SELECT p FROM Product p
//                LEFT JOIN FETCH p.favorites
//
//                WHERE p.id = :productID
//            """)
//    Product findByIdWithLikes(Long id);

//    @EntityGraph(attributePaths = {"comments", "images", "favorites"})
//    Optional<Product> getProductById(Long id);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.comments WHERE p.id = :id")
    Optional<Product> fetchProductWithComments(Long id);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.favorites WHERE p.id = :id")
    Optional<Product> fetchProductWithFavorites(Long id);
}
