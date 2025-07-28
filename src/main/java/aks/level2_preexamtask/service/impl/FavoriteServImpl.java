package aks.level2_preexamtask.service.impl;

import aks.level2_preexamtask.config.JwtUtil;
import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;
import aks.level2_preexamtask.entities.Favorite;
import aks.level2_preexamtask.entities.Product;
import aks.level2_preexamtask.entities.User;
import aks.level2_preexamtask.exceptions.NotFoundException;
import aks.level2_preexamtask.mapper.product.ProductResponseMapper;
import aks.level2_preexamtask.repositories.FavoriteRepo;
import aks.level2_preexamtask.repositories.ProductRepo;
import aks.level2_preexamtask.repositories.UserRepo;
import aks.level2_preexamtask.service.FavoriteServ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServImpl implements FavoriteServ {
    private final JwtUtil jwtUtil;
    private final ProductRepo productRepo;
    private final FavoriteRepo favoriteRepo;
   private final ProductResponseMapper mapper;

    @Override
    public SimpleResponse deleteToFavorite(Long id) {
        User user = jwtUtil.getUserViaToken();
        Product product = productRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found! {" + this.getClass().getName())
        );
        Favorite favorite = (Favorite) favoriteRepo.findByUserAndProduct(user, product)
                .orElseThrow(() -> new NotFoundException("Product is not in Favorites"));

        favoriteRepo.delete(favorite);
        return new SimpleResponse(HttpStatus.OK, "Product deleted from Favorites!");


        //return new SimpleResponse(HttpStatus.OK,"Product is not favorite");

    }

    @Override
    public List<ProductResponseForGetAll> getFavorites() {
        User user =  jwtUtil.getUserViaToken();
       List<Favorite> favorites= favoriteRepo.findByUser(user);
        return favorites.stream()
                .map(Favorite::getProduct)
                .map(mapper::toResponseForGetAll)
                .collect(Collectors.toList());
//       List<Product> productList = new ArrayList<>();
//        for (Favorite f:list
//             ) {
//            productList.add(productRepo.findById(f.getProduct().getId()).orElseThrow(
//                    ()->new NotFoundException("Product not found {"+this.getClass().getName())
//            ));
//        }
//        return productList.stream().map(mapper::toResponseForGetAll).collect(Collectors.toList());

    }

    @Override
    public SimpleResponse addToFavorite(Long id) {

        User user = jwtUtil.getUserViaToken();
        Product product = productRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found! {" + this.getClass().getName())
        );
        boolean alreadyFavorite = favoriteRepo.findByUserAndProduct(user, product).isPresent();

        if (!alreadyFavorite) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setProduct(product);
            favoriteRepo.save(favorite);
            return new SimpleResponse(HttpStatus.OK, "Product added to Favorites!");

        }
        return new SimpleResponse(HttpStatus.OK, "Product is already in Favorites");

    }
}
