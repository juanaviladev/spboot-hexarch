package es.cloudapps.hexarch.infrastructure.events.consumer;

import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort;
import es.cloudapps.hexarch.infrastructure.events.QueueNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventConsumer {

    private final ProductCommandServicePort productService;

    public ProductEventConsumer(ProductCommandServicePort productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = QueueNames.REGISTER_NEW_PRODUCT, ackMode = "AUTO")
    public void consumeUpdateCartView(ProductCommandServicePort.RegisterNewReq req) {
        productService.registerNew(req);
    }

    @RabbitListener(queues = QueueNames.REMOVE_PRODUCT, ackMode = "AUTO")
    public void consumeRegisterNewCart(ProductCommandServicePort.RemoveReq req) {
        productService.remove(req);
    }


}
