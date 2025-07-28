package aks.level2_preexamtask.service;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.basketDto.BasketResponse;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;

import java.util.List;

public interface BasketServ {
    SimpleResponse addToBasket(Long productId);

    SimpleResponse removeFromBasket(Long productId);

    BasketResponse getProducts();

    SimpleResponse clear();
}
