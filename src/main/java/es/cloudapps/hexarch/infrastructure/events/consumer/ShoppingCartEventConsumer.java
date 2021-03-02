package es.cloudapps.hexarch.infrastructure.events.consumer;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartCommandServicePort;
import es.cloudapps.hexarch.infrastructure.events.QueueNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartEventConsumer {

    private final ShoppingCartCommandServicePort shoppingCartService;

    public ShoppingCartEventConsumer(ShoppingCartCommandServicePort shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RabbitListener(queues = QueueNames.UPDATE_CART_VIEW, ackMode = "AUTO")
    public void consumeUpdateCartView(Integer cartId) {
        // ...
    }

    @RabbitListener(queues = QueueNames.REGISTER_NEW_CART, ackMode = "AUTO")
    public void publishRegisterNewCart(ShoppingCartCommandServicePort.RegisterNewCartReq cartDto) {
        shoppingCartService.registerNewCart(cartDto);
    }

    @RabbitListener(queues = QueueNames.ADD_TO_CART, ackMode = "AUTO")
    public void consumeAddToCart(ShoppingCartCommandServicePort.AddToCartReq req) {
        shoppingCartService.addToCart(req);
    }

    @RabbitListener(queues = QueueNames.CHECKOUT_CART, ackMode = "AUTO")
    public void consumeCheckoutCart(ShoppingCartCommandServicePort.CheckoutCartReq req) {
        shoppingCartService.checkoutCart(req);
    }

    @RabbitListener(queues = QueueNames.REMOVE_FROM_CART, ackMode = "AUTO")
    public void consumeRemoveFromCart(ShoppingCartCommandServicePort.RemoveFromCartReq req) {
        shoppingCartService.removeFromCart(req);
    }

    @RabbitListener(queues = QueueNames.REMOVE_CART, ackMode = "AUTO")
    public void consumeRemoveCart(ShoppingCartCommandServicePort.RemoveCartReq req) {
        shoppingCartService.removeCart(req);
    }

}
