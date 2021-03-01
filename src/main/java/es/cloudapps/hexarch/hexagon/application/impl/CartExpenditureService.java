package es.cloudapps.hexarch.hexagon.application.impl;

import es.cloudapps.hexarch.hexagon.application.CartExpenditureServicePort;
import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;
import es.cloudapps.hexarch.hexagon.domain.services.ShoppingCartRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CartExpenditureService implements CartExpenditureServicePort {

    private final ShoppingCartRepository shoppingCartRepository;

    public CartExpenditureService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public CartExpenditureResp getCartExpenditure(CartExpenditureReq params) {
        return new CartExpenditureResp(map(shoppingCartRepository.getAllCompleted()));
    }

    private List<ExpenditureDto> map(List<ShoppingCart> items) {
        return items
                .stream()
                .map(item -> new ExpenditureDto(item.id(), item.totalQuantity()))
                .collect(Collectors.toList());
    }

}