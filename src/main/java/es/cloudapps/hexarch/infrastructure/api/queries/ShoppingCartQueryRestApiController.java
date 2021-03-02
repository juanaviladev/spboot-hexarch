package es.cloudapps.hexarch.infrastructure.api.queries;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartQueryServicePort;
import es.cloudapps.hexarch.hexagon.application.ShoppingCartQueryServicePort.GetCartReq;
import es.cloudapps.hexarch.hexagon.application.ShoppingCartQueryServicePort.GetCartResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShoppingCartQueryRestApiController {

    private final ShoppingCartQueryServicePort shoppingCartService;

    public ShoppingCartQueryRestApiController(ShoppingCartQueryServicePort shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/shoppingcarts/{id}")
    public GetCartResp get(@PathVariable Integer id) {
        return shoppingCartService.getCart(new GetCartReq(id));
    }

}
