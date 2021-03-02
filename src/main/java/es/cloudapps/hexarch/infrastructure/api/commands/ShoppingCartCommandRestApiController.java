package es.cloudapps.hexarch.infrastructure.api.commands;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartCommandServicePort.*;
import es.cloudapps.hexarch.infrastructure.events.publisher.ShoppingCartEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShoppingCartCommandRestApiController {

    private final ShoppingCartEventPublisher shoppingCartEventPublisher;

    public ShoppingCartCommandRestApiController(ShoppingCartEventPublisher shoppingCartEventPublisher) {
        this.shoppingCartEventPublisher = shoppingCartEventPublisher;
    }

    @PostMapping("/shoppingcarts")
    public void post(@RequestBody RegisterNewCartReq cartDto) {
        shoppingCartEventPublisher.publishRegisterNewCart(cartDto);
    }

    @PostMapping("/shoppingcarts/{cartId}/product/{prodId}/quantity/{prodQuantity}")
    public void post(@PathVariable Integer cartId, @PathVariable Integer prodId,
                     @PathVariable Integer prodQuantity) {
        shoppingCartEventPublisher.publishAddToCart(new AddToCartReq(cartId, prodId, prodQuantity));
    }

    @PatchMapping("/shoppingcarts/{id}")
    public void patch(@PathVariable Integer id) {
        shoppingCartEventPublisher.publishUpdateCartView(id);
        shoppingCartEventPublisher.publishCheckoutCart(new CheckoutCartReq(id));
    }

    @DeleteMapping("/shoppingcarts/{cartId}/product/{prodId}")
    public void delete(@PathVariable Integer cartId, @PathVariable Integer prodId) {
        shoppingCartEventPublisher.publishRemoveFromCart(new RemoveFromCartReq(cartId, prodId));
    }

    @DeleteMapping("/shoppingcarts/{id}")
    public void delete(@PathVariable Integer id) {
        shoppingCartEventPublisher.publishRemoveCart(new RemoveCartReq(id));
    }

}
