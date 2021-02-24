package es.cloudapps.hexarch.hexagon.domain.services;

import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartRepository {
    ShoppingCart save(ShoppingCart cart);
    void remove(ShoppingCart cart);
    Optional<ShoppingCart> get(Integer cartId);
}
