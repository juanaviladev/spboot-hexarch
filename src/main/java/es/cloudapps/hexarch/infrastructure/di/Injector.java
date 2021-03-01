package es.cloudapps.hexarch.infrastructure.di;

import es.cloudapps.hexarch.hexagon.application.CartExpenditureServicePort;
import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort;
import es.cloudapps.hexarch.hexagon.application.ProductQueryServicePort;
import es.cloudapps.hexarch.hexagon.application.ShoppingCartServicePort;
import es.cloudapps.hexarch.hexagon.application.impl.CartExpenditureService;
import es.cloudapps.hexarch.hexagon.application.impl.ProductCommandService;
import es.cloudapps.hexarch.hexagon.application.impl.ProductQueryService;
import es.cloudapps.hexarch.hexagon.application.impl.ShoppingCartService;
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
    public ProductCommandServicePort productCommandServicePort(ProductRepository repository){
        return new ProductCommandService(repository);
    }

    @Bean
    public ShoppingCartServicePort shoppingCartServicePort(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, CheckoutService checkoutService) {
        return new ShoppingCartService(
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
