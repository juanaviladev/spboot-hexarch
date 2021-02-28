package es.cloudapps.hexarch.infrastructure.api;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartServicePort;
import es.cloudapps.hexarch.hexagon.application.ShoppingCartServicePort.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartExpenditureRestApiController {

    private final ShoppingCartServicePort shoppingCartService;

    public CartExpenditureRestApiController(ShoppingCartServicePort shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/cartexpenditure")
    public CartExpenditureResp getCartExpenditure() {
        return shoppingCartService.getCartExpenditure(new CartExpenditureReq());
    }

}
