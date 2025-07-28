package aks.level2_preexamtask.service.impl;

import aks.level2_preexamtask.config.JwtUtil;
import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.basketDto.BasketResponse;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;
import aks.level2_preexamtask.entities.Basket;
import aks.level2_preexamtask.entities.Product;
import aks.level2_preexamtask.entities.User;
import aks.level2_preexamtask.exceptions.NotFoundException;
import aks.level2_preexamtask.mapper.product.ProductResponseMapper;
import aks.level2_preexamtask.repositories.BasketRepo;
import aks.level2_preexamtask.repositories.ProductRepo;
import aks.level2_preexamtask.service.BasketServ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BasketServImpl implements BasketServ {
    private final BasketRepo basketRepo;
    private  final JwtUtil jwtUtil;
    private final ProductRepo productRepo;
    private final ProductResponseMapper mapper;

    @Override
    public BasketResponse getProducts() {
        User user = jwtUtil.getUserViaToken();
        Basket userBasket =(Basket) basketRepo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Basket not found for user"));
        List<Product> products = userBasket.getProducts();
        if (products == null || products.isEmpty()) {
            return new BasketResponse();
        }
        BasketResponse response =  new BasketResponse();
        int count = 0;
        int totalPrice = 0;
        for (Product p:products
             ) {
            response.getProducts().add(mapper.toResponseForGetAll(p));
            count++;
            totalPrice+=p.getPrice();
        }
        response.setCount(count);
        response.setTotalPrice(totalPrice);
        return response;
    }

    @Override
    public SimpleResponse clear() {
        User user = jwtUtil.getUserViaToken();

        Basket userBasket =(Basket) basketRepo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Basket not found for user"));

        if (userBasket.getProducts() != null) {
            userBasket.getProducts().clear();
        }

        basketRepo.save(userBasket);

        return new SimpleResponse(HttpStatus.OK, "Basket cleared successfully");
    }

    @Override
    public SimpleResponse removeFromBasket(Long productId) {
        User user = jwtUtil.getUserViaToken();

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found {" + this.getClass().getName() + "}"));

        Basket userBasket =(Basket) basketRepo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Basket not found for user"));

        List<Product> products = userBasket.getProducts();

        if (products == null || products.isEmpty()) {
            return new SimpleResponse(HttpStatus.OK, "Basket is already empty");
        }

        boolean removed = products.remove(product);

        if (removed) {
            basketRepo.save(userBasket);
            return new SimpleResponse(HttpStatus.OK, "Product is removed from Basket");
        }

        return new SimpleResponse(HttpStatus.OK, "Product not found in Basket");
    }

    @Override
    public SimpleResponse addToBasket(Long productId) {
        User user =  jwtUtil.getUserViaToken();
        Product product = productRepo.findById(productId).orElseThrow(
                ()->new NotFoundException("Product not found {"+this.getClass().getName())
        );
        Basket userBasket =(Basket)basketRepo.findByUser(user).orElseThrow();
//        if (userBasket.getProducts() == null) {
//            userBasket.setProducts(new ArrayList<>());
//        }
          if(!userBasket.getProducts().contains(product))  {
              userBasket.getProducts().add(product);
              userBasket.setUser(user);
              basketRepo.save(userBasket);
              return new SimpleResponse(HttpStatus.OK,"Product added to Basket");
          }
     //   basketRepo.save()
        return new SimpleResponse(HttpStatus.OK,"Product already in Basket");
    }
}
