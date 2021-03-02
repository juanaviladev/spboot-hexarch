package es.cloudapps.hexarch.infrastructure.api.commands;

import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort.RegisterNewReq;
import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort.RemoveReq;
import es.cloudapps.hexarch.infrastructure.events.publisher.ProductEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductsCommandRestApiController {

    private final ProductEventPublisher productEventPublisher;

    public ProductsCommandRestApiController(ProductEventPublisher productEventPublisher) {
        this.productEventPublisher = productEventPublisher;
    }

    @PostMapping("/products")
    public void post(@RequestBody RegisterNewReq registerNewReq) {
        productEventPublisher.publishRegisterNewProduct(registerNewReq);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        productEventPublisher.publishRemoveProduct(new RemoveReq(id));
    }

}
