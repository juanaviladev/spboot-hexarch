package es.cloudapps.hexarch.infrastructure.api.queries;

import es.cloudapps.hexarch.hexagon.application.ProductQueryServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static es.cloudapps.hexarch.hexagon.application.ProductQueryServicePort.*;

@RestController
@RequestMapping("/api")
public class ProductsQueryRestApiController {

    private final ProductQueryServicePort productService;

    public ProductsQueryRestApiController(ProductQueryServicePort productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public FindAllResp getAll() {
        return productService.findAll(new FindAllReq());
    }

    @GetMapping("/products/{id}")
    public FindResp get(@PathVariable Integer id) {
        return productService.find(new FindReq(id));
    }

}
