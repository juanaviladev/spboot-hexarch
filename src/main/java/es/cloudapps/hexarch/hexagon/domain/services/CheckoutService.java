package es.cloudapps.hexarch.hexagon.domain.services;

import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;

public interface CheckoutService {
    boolean validateCart(ShoppingCart shoppingCart);
}
