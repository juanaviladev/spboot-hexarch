package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model;

import es.cloudapps.hexarch.hexagon.domain.model.CartItem;
import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;

import java.util.List;

public class FillableShoppingCart extends ShoppingCart {

    public FillableShoppingCart(Integer id, List<CartItem> items, ShoppingCart.Status status) {
        super(id, items, status);
    }
}
