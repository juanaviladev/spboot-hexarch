package es.cloudapps.hexarch.infrastructure.hexagon.application;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartQueryServicePort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Primary
@Service
@Transactional
public class TransactionalShoppingCartQueryService implements ShoppingCartQueryServicePort {

    private final ShoppingCartQueryServicePort shoppingCartServicePort;

    public TransactionalShoppingCartQueryService(ShoppingCartQueryServicePort shoppingCartServicePort) {
        this.shoppingCartServicePort = shoppingCartServicePort;
    }

    @Override
    public GetCartResp getCart(GetCartReq params) {
        return shoppingCartServicePort.getCart(params);
    }

}
