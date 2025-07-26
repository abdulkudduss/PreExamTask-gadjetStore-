package aks.level2_preexamtask.api.product;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.productDto.ProductRequest;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;
import aks.level2_preexamtask.entities.Product;
import aks.level2_preexamtask.enums.Category;
import aks.level2_preexamtask.enums.Role;
import aks.level2_preexamtask.service.ProductServ;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductApi {
    private final ProductServ productServ;
    @PostMapping("/admin/product")
    @Secured("ADMIN")
    public SimpleResponse createProduct(@RequestBody ProductRequest request){
        return productServ.save(request);
    }
    @PutMapping("/admin/product/{id}")
    @Secured("ADMIN")
    public SimpleResponse updateProduct (@PathVariable Long id,@RequestBody ProductRequest request){
        return productServ.update(id,request);
    }
    @DeleteMapping("/admin/product/{id}")
    @Secured("ADMIN")
    public SimpleResponse deleteProduct (@PathVariable Long id){
        return productServ.delete(id);
    }
    @GetMapping("/products")
    public Page<ProductResponseForGetAll> getAllProducts(
            @RequestParam(required = false) List<Category> categories,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        return productServ.getAll(categories, minPrice, maxPrice, page, size, sortBy, order);
    }
}
