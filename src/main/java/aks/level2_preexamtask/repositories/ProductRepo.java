package aks.level2_preexamtask.repositories;

import aks.level2_preexamtask.entities.Product;

import aks.level2_preexamtask.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
//    Long id;
//    String name;
//    String price;
//    String madeIn;
//    Category category;
//    Brand brand;

//    @Query ("""
//            select new gadgetStore.dto.productDto.ProductResponseForGetAll(
//                        p.id, p.name, p.price, p.madeIn, p.category, p.brand
//                        )
//            from Product p
//            """)
//    List<ProductResponseForGetAll> getAll();
//
//    @Query("""
//            select new gadgetStore.dto.productDto.ProductResponseForGetAll(
//                        p.id, p.name, p.price, p.madeIn, p.category, p.brand
//                        )
//            from Product p
//                        where p.category = :category
//                                    and p.price <= :price
//            """)
//    public List<ProductResponseForGetAll> getAllByCategoryAndPrice(Category category, Double price);
//
//    default Product getProductByIdOrException(Long id){
//        return findById(id).orElseThrow(
//                () -> new NotFoundException("Product with id " + id + " not found!"));
//    }
}
