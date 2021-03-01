package es.cloudapps.hexarch.infrastructure.api;

import es.cloudapps.hexarch.hexagon.application.CartExpenditureServicePort;
import es.cloudapps.hexarch.hexagon.application.CartExpenditureServicePort.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartExpenditureRestApiController {

    private final CartExpenditureServicePort cartExpenditureService;

    public CartExpenditureRestApiController(CartExpenditureServicePort cartExpenditureService) {
        this.cartExpenditureService = cartExpenditureService;
    }

    @GetMapping("/cartexpenditure")
    public CartExpenditureResp getCartExpenditure() {
        return cartExpenditureService.getCartExpenditure(new CartExpenditureReq());
    }

}
