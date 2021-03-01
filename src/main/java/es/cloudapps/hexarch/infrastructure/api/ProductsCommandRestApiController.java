package es.cloudapps.hexarch.infrastructure.api;

import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort;
import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductsCommandRestApiController {

    private final ProductCommandServicePort productService;

    public ProductsCommandRestApiController(ProductCommandServicePort productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public RegisterNewResp post(@RequestBody RegisterNewReq registerNewReq) {
        return productService.registerNew(registerNewReq);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        productService.remove(new RemoveReq(id));
    }

}
