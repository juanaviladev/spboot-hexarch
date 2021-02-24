package es.cloudapps.hexarch.infrastructure.api;

import es.cloudapps.hexarch.hexagon.application.ProductServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static es.cloudapps.hexarch.hexagon.application.ProductServicePort.*;

@RestController
@RequestMapping("/api")
public class ProductsRestApiController {

    @Autowired
    private ProductServicePort productService;

    @GetMapping("/products")
    public FindAllResp getAll() {
        return productService.findAll(new FindAllReq());
    }

    @PostMapping("/products")
    public RegisterNewResp post(@RequestBody RegisterNewReq registerNewReq) {
        return productService.registerNew(registerNewReq);
    }

    @GetMapping("/products/{id}")
    public FindResp get(@PathVariable Integer id) {
        return productService.find(new FindReq(id));
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        productService.remove(new RemoveReq(id));
    }
}
