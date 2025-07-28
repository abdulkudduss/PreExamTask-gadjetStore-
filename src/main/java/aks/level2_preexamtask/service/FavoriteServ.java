package aks.level2_preexamtask.service;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;

import java.util.List;

public interface FavoriteServ {
    SimpleResponse addToFavorite(Long id);

    SimpleResponse deleteToFavorite(Long id);

   List< ProductResponseForGetAll> getFavorites();
}
