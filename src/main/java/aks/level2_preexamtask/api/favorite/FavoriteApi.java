package aks.level2_preexamtask.api.favorite;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;
import aks.level2_preexamtask.enums.Role;
import aks.level2_preexamtask.service.FavoriteServ;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {

    private final FavoriteServ favoriteServ;

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public SimpleResponse addToFavorite(@PathVariable Long id) {
        return favoriteServ.addToFavorite(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public SimpleResponse deleteToFavorite(@PathVariable Long id) {
        return favoriteServ.deleteToFavorite(id);
    }
    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public List<ProductResponseForGetAll> getFavorites(){
        return favoriteServ.getFavorites();
    }
}
