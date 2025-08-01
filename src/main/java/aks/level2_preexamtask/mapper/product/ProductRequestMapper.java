package aks.level2_preexamtask.mapper.product;

import aks.level2_preexamtask.dto.productDto.ProductRequest;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;
import aks.level2_preexamtask.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
    ProductRequest productDto(Product product);

    Product toEntity (ProductRequest request);


}
