package aks.level2_preexamtask.service;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.productDto.ProductRequest;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;
import aks.level2_preexamtask.enums.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductServ {
    SimpleResponse save(ProductRequest request);

    SimpleResponse update(Long id, ProductRequest request);

    SimpleResponse delete(Long id);

    Page<ProductResponseForGetAll> getAll(List<Category> categories, Integer minPrice, Integer maxPrice, int page, int size, String sortBy, String order);
}
