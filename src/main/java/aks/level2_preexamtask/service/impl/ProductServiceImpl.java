package aks.level2_preexamtask.service.impl;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.productDto.ProductRequest;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;
import aks.level2_preexamtask.entities.Product;
import aks.level2_preexamtask.enums.Category;
import aks.level2_preexamtask.exceptions.NotFoundException;
import aks.level2_preexamtask.mapper.product.ProductRequestMapper;
import aks.level2_preexamtask.mapper.product.ProductResponseMapper;
import aks.level2_preexamtask.repositories.BrandRepo;
import aks.level2_preexamtask.repositories.ProductRepo;
import aks.level2_preexamtask.service.ProductServ;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductServ {
    private final ProductRepo productRepo;
    private final ProductRequestMapper mapper;
    private final BrandRepo brandRepo;
    private final ProductResponseMapper productResponseMapper;

    @Override
    public SimpleResponse save(ProductRequest request) {
        Product product  = mapper.toEntity(request);
        System.out.println(product);
        product.setBrand(brandRepo.getBrandByIdOrIdException(request.getBrandID()));
        productRepo.save(product);
        return new SimpleResponse(HttpStatus.CREATED, "Product is created!");

    }

    @Override
    public SimpleResponse update(Long id, ProductRequest request) {
       Product product= productRepo.findById(id).orElseThrow(
               ()->   new NotFoundException("product with id:"+id+" does not exists")
       );
       product.setName(request.getName());
       product.setCategory(request.getCategory());
       product.setDescription(request.getDescription());
       product.setPrice(request.getPrice());
       product.setImages(request.getImages());
       product.setBrand(brandRepo.getBrandByIdOrIdException(request.getBrandID()));

       productRepo.save(product);

        return new SimpleResponse(HttpStatus.OK,"Product updated!") ;
    }

    @Override
    public SimpleResponse delete(Long id) {
        productRepo.delete(productRepo.findById(id).orElseThrow(
                ()->   new NotFoundException("product with id:"+id+" does not exists")));
        return new SimpleResponse(HttpStatus.OK,"Product is deleted!");
    }

    @Override
    public Page<ProductResponseForGetAll> getAll(List<Category> categories, Integer minPrice, Integer maxPrice, int page, int size, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> pageResult = productRepo
                .findAllWithFilters(categories, minPrice, maxPrice,(Integer)categories.size(),  pageable);

        return pageResult.map(productResponseMapper::toResponseForGetAll);

    }
}
