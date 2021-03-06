package es.cloudapps.hexarch.infrastructure.hexagon.application;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartServicePort;
import es.cloudapps.hexarch.hexagon.domain.exception.NotAvailableProductsException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Primary
@Service
@Transactional
public class TransactionalShoppingCartService implements ShoppingCartServicePort {

    private ShoppingCartServicePort shoppingCartServicePort;

    public TransactionalShoppingCartService(ShoppingCartServicePort shoppingCartServicePort) {
        this.shoppingCartServicePort = shoppingCartServicePort;
    }

    @Override
    public RegisterNewCartResp registerNewCart(RegisterNewCartReq params) {
        return shoppingCartServicePort.registerNewCart(params);
    }

    @Override
    public CheckoutCartResp checkoutCart(CheckoutCartReq params) throws NotAvailableProductsException {
        return shoppingCartServicePort.checkoutCart(params);
    }

    @Override
    public GetCartResp getCart(GetCartReq params) {
        return shoppingCartServicePort.getCart(params);
    }

    @Override
    public RemoveCartResp removeCart(RemoveCartReq params) {
        return shoppingCartServicePort.removeCart(params);
    }

    @Override
    public AddToCartResp addToCart(AddToCartReq params) {
        return shoppingCartServicePort.addToCart(params);
    }

    @Override
    public RemoveFromCartResp removeFromCart(RemoveFromCartReq params) {
        return shoppingCartServicePort.removeFromCart(params);
    }
}
