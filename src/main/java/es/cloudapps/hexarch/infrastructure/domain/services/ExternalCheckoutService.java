package es.cloudapps.hexarch.infrastructure.domain.services;

import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;
import es.cloudapps.hexarch.hexagon.domain.services.CheckoutService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExternalCheckoutService implements CheckoutService {

    private final Random rand = new Random();

    @Override
    public boolean validateCart(ShoppingCart shoppingCart) {
        return rand.nextBoolean();
    }
}
