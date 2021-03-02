package es.cloudapps.hexarch.infrastructure.di;

import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort;
import es.cloudapps.hexarch.hexagon.application.ProductQueryServicePort;
import es.cloudapps.hexarch.hexagon.application.ShoppingCartCommandServicePort;
import es.cloudapps.hexarch.hexagon.application.impl.ProductCommandService;
import es.cloudapps.hexarch.hexagon.application.impl.ProductQueryService;
import es.cloudapps.hexarch.hexagon.application.impl.ShoppingCartCommandService;
import es.cloudapps.hexarch.hexagon.application.impl.ShoppingCartQueryService;
import es.cloudapps.hexarch.hexagon.domain.services.CheckoutService;
import es.cloudapps.hexarch.hexagon.domain.services.ProductRepository;
import es.cloudapps.hexarch.hexagon.domain.services.ShoppingCartRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Injector {

    @Bean
    public ProductQueryServicePort productQueryServicePort(ProductRepository repository) {
        return new ProductQueryService(repository);
    }

    @Bean
    public ProductCommandServicePort productCommandServicePort(ProductRepository repository) {
        return new ProductCommandService(repository);
    }

    @Bean
    public ShoppingCartQueryService shoppingCartQueryServicePort(ShoppingCartRepository shoppingCartRepository,
                                                                     ProductRepository productRepository, CheckoutService checkoutService) {
        return new ShoppingCartQueryService(shoppingCartRepository);
    }

    @Bean
    public ShoppingCartCommandServicePort shoppingCartCommandServicePort(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, CheckoutService checkoutService) {
        return new ShoppingCartCommandService(
                shoppingCartRepository,
                productRepository,
                checkoutService
        );
    }
}
