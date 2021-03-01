package es.cloudapps.hexarch.infrastructure.di;

import es.cloudapps.hexarch.hexagon.application.*;
import es.cloudapps.hexarch.hexagon.application.impl.*;
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
    public ShoppingCartQueryServicePort shoppingCartQueryServicePort(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, CheckoutService checkoutService) {
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

    @Bean
    public CartExpenditureServicePort cartExpenditureServicePort(ShoppingCartRepository shoppingCartRepository) {
        return new CartExpenditureService(shoppingCartRepository);
    }
}
