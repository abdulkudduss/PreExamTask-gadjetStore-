package aks.level2_preexamtask.api.basket;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.basketDto.BasketResponse;
import aks.level2_preexamtask.dto.productDto.ProductResponseForGetAll;
import aks.level2_preexamtask.service.BasketServ;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/basket")
@RequiredArgsConstructor
public class BasketApi {

    private final BasketServ basketServ;

    @PostMapping("/add/{productId}")
    @PreAuthorize("hasRole('USER')")
    public SimpleResponse addToBasket(@PathVariable Long productId) {
        return basketServ.addToBasket(productId);
    }

    @DeleteMapping("/remove/{productId}")
    @PreAuthorize("hasRole('USER')")
    public SimpleResponse removeFromBasket(@PathVariable Long productId) {
        return basketServ.removeFromBasket(productId);
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public BasketResponse getBasketProducts() {
        return basketServ.getProducts();
    }

    @DeleteMapping("/clear")
    @PreAuthorize("hasRole('USER')")
    public SimpleResponse clearBasket() {
        return basketServ.clear();
    }
}
