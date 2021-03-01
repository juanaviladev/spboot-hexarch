package es.cloudapps.hexarch.infrastructure.api;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartCommandServicePort;
import es.cloudapps.hexarch.hexagon.application.ShoppingCartCommandServicePort.*;
import es.cloudapps.hexarch.hexagon.domain.exception.NotAvailableProductsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShoppingCartCommandRestApiController {

    private final ShoppingCartCommandServicePort shoppingCartService;

    public ShoppingCartCommandRestApiController(ShoppingCartCommandServicePort shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/shoppingcarts")
    public RegisterNewCartResp post(@RequestBody RegisterNewCartReq cartDto) {
        return shoppingCartService.registerNewCart(cartDto);
    }

    @PostMapping("/shoppingcarts/{cartId}/product/{prodId}/quantity/{prodQuantity}")
    public void post(@PathVariable Integer cartId, @PathVariable Integer prodId,
                     @PathVariable Integer prodQuantity) {
        shoppingCartService.addToCart(new AddToCartReq(cartId, prodId, prodQuantity));
    }

    @PatchMapping("/shoppingcarts/{id}")
    public ResponseEntity<?> patch(@PathVariable Integer id) {
        try {
            shoppingCartService.checkoutCart(new CheckoutCartReq(id));
            return ResponseEntity.noContent().build();
        } catch (NotAvailableProductsException ex) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .build();
        }
    }

    @DeleteMapping("/shoppingcarts/{cartId}/product/{prodId}")
    public void delete(@PathVariable Integer cartId, @PathVariable Integer prodId) {
        shoppingCartService.removeFromCart(new RemoveFromCartReq(cartId, prodId));
    }

}
