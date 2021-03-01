package es.cloudapps.hexarch.infrastructure.hexagon.application;

import es.cloudapps.hexarch.hexagon.application.ProductQueryServicePort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Primary
@Service
@Transactional
public class TransactionalProductQueryService implements ProductQueryServicePort {

    private final ProductQueryServicePort productQueryServicePort;

    public TransactionalProductQueryService(ProductQueryServicePort productQueryServicePort) {
        this.productQueryServicePort = productQueryServicePort;
    }

    @Override
    public FindResp find(FindReq params) {
        return productQueryServicePort.find(params);
    }

    @Override
    public FindAllResp findAll(FindAllReq params) {
        return productQueryServicePort.findAll(params);
    }
}