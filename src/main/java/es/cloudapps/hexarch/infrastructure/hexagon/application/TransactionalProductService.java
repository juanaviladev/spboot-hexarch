package es.cloudapps.hexarch.infrastructure.hexagon.application;

import es.cloudapps.hexarch.hexagon.application.ProductServicePort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Primary
@Service
@Transactional
public class TransactionalProductService implements ProductServicePort {

    private ProductServicePort productServicePort;

    public TransactionalProductService(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @Override
    public RegisterNewResp registerNew(RegisterNewReq params) {
        return productServicePort.registerNew(params);
    }

    @Override
    public RemoveResp remove(RemoveReq params) {
        return productServicePort.remove(params);
    }

    @Override
    public FindResp find(FindReq params) {
        return productServicePort.find(params);
    }

    @Override
    public FindAllResp findAll(FindAllReq params) {
        return productServicePort.findAll(params);
    }
}