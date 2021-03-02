package es.cloudapps.hexarch.infrastructure.events.publisher;

import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort;
import es.cloudapps.hexarch.infrastructure.events.QueueNames;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventPublisher {

    private final RabbitTemplate rt;

    public ProductEventPublisher(RabbitTemplate rt) {
        this.rt = rt;
    }

    public void publishRegisterNewProduct(ProductCommandServicePort.RegisterNewReq req) {
        rt.convertAndSend(QueueNames.REGISTER_NEW_PRODUCT, req);
    }

    public void publishRemoveProduct(ProductCommandServicePort.RemoveReq req) {
        rt.convertAndSend(QueueNames.REMOVE_PRODUCT, req);
    }

}
