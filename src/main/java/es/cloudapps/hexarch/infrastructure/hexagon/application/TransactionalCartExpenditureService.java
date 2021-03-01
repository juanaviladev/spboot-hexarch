package es.cloudapps.hexarch.infrastructure.hexagon.application;

import es.cloudapps.hexarch.hexagon.application.CartExpenditureServicePort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Primary
@Service
@Transactional
public class TransactionalCartExpenditureService implements CartExpenditureServicePort {

    private final CartExpenditureServicePort cartExpenditureService;

    public TransactionalCartExpenditureService(CartExpenditureServicePort cartExpenditureService) {
        this.cartExpenditureService = cartExpenditureService;
    }

    @Override
    public CartExpenditureResp getCartExpenditure(CartExpenditureReq params) {
        return cartExpenditureService.getCartExpenditure(params);
    }
}
