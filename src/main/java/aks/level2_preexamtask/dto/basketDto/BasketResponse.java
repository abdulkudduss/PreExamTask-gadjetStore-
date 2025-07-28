package aks.level2_preexamtask.dto.basketDto;

import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class BasketResponse {
    Long id;
    List<ProductResponseForGetAll> products = new ArrayList<>();
    double totalPrice;
    int count;

    public BasketResponse() {
    }

    public BasketResponse(Long id, List<ProductResponseForGetAll> products, double totalPrice, int count) {
        this.id = id;
        this.products = products;
        this.totalPrice = totalPrice;
        this.count = count;
    }
}
