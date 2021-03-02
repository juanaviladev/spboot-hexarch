package es.cloudapps.hexarch.infrastructure.hexagon.application;

import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Primary
@Service
@Transactional
public class TransactionalProductCommandService implements ProductCommandServicePort {

    private final ProductCommandServicePort productServicePort;

    public TransactionalProductCommandService(ProductCommandServicePort productServicePort) {
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


}
