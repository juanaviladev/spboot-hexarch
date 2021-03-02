package es.cloudapps.hexarch.infrastructure.events;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue updateCartViewQueue() {
        return new Queue(QueueNames.UPDATE_CART_VIEW, false);
    }

    @Bean
    public Queue registerNewCartQueue() {
        return new Queue(QueueNames.REGISTER_NEW_CART, false);
    }

    @Bean
    public Queue addToCartQueue() {
        return new Queue(QueueNames.ADD_TO_CART, false);
    }

    @Bean
    public Queue checkoutCartQueue() {
        return new Queue(QueueNames.CHECKOUT_CART, false);
    }

    @Bean
    public Queue removeFromCartQueue() {
        return new Queue(QueueNames.REMOVE_FROM_CART, false);
    }

    @Bean
    public Queue removeCartQueue() {
        return new Queue(QueueNames.REMOVE_CART, false);
    }

    @Bean
    public Queue registerNewProductQueue() {
        return new Queue(QueueNames.REGISTER_NEW_PRODUCT, false);
    }

    @Bean
    public Queue deleteProductQueue() {
        return new Queue(QueueNames.REMOVE_PRODUCT, false);
    }

}