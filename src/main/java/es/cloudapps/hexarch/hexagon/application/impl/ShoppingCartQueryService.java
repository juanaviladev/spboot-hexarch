package es.cloudapps.hexarch.hexagon.application.impl;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartQueryServicePort;
import es.cloudapps.hexarch.hexagon.domain.exception.NotFoundException;
import es.cloudapps.hexarch.hexagon.domain.model.CartItem;
import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;
import es.cloudapps.hexarch.hexagon.domain.services.ShoppingCartRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartQueryService implements ShoppingCartQueryServicePort {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartQueryService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public GetCartResp getCart(GetCartReq params) {
        ShoppingCart shoppingCart = shoppingCartRepository.get(params.id)
                .orElseThrow(NotFoundException::new);

        return new GetCartResp(shoppingCart.id(), map(shoppingCart.items()), shoppingCart.status().toString(), shoppingCart.totalQuantity());
    }

    private List<CartItemDto> map(List<CartItem> items) {
        return items.stream().map(item -> new CartItemDto(item.product().id(), item.quantity()))
                .collect(Collectors.toList());
    }

}
