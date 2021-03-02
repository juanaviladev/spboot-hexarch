package es.cloudapps.hexarch.infrastructure.events.publisher;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartCommandServicePort;
import es.cloudapps.hexarch.infrastructure.events.QueueNames;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartEventPublisher {

    private final RabbitTemplate rt;

    public ShoppingCartEventPublisher(RabbitTemplate rt) {
        this.rt = rt;
    }

    public void publishUpdateCartView(Integer cartId) {
        rt.convertAndSend(QueueNames.UPDATE_CART_VIEW, cartId);
    }

    public void publishRegisterNewCart(ShoppingCartCommandServicePort.RegisterNewCartReq cartDto) {
        rt.convertAndSend(QueueNames.REGISTER_NEW_CART, cartDto);
    }

    public void publishAddToCart(ShoppingCartCommandServicePort.AddToCartReq req) {
        rt.convertAndSend(QueueNames.ADD_TO_CART, req);
    }

    public void publishCheckoutCart(ShoppingCartCommandServicePort.CheckoutCartReq req) {
        rt.convertAndSend(QueueNames.CHECKOUT_CART, req);
    }

    public void publishRemoveFromCart(ShoppingCartCommandServicePort.RemoveFromCartReq req) {
        rt.convertAndSend(QueueNames.REMOVE_FROM_CART, req);
    }

    public void publishRemoveCart(ShoppingCartCommandServicePort.RemoveCartReq req) {
        rt.convertAndSend(QueueNames.REMOVE_CART, req);
    }

}
