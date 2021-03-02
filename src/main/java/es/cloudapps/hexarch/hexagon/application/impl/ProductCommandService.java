package es.cloudapps.hexarch.hexagon.application.impl;

import es.cloudapps.hexarch.hexagon.application.ProductCommandServicePort;
import es.cloudapps.hexarch.hexagon.domain.exception.NotFoundException;
import es.cloudapps.hexarch.hexagon.domain.model.Product;
import es.cloudapps.hexarch.hexagon.domain.services.ProductRepository;

public class ProductCommandService implements ProductCommandServicePort {

    private final ProductRepository productRepository;

    public ProductCommandService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public RegisterNewResp registerNew(RegisterNewReq params) {
        Product product = new Product(new Product.Name(params.name), params.cost);
        Product saved = productRepository.save(product);
        return new RegisterNewResp(saved.id(), saved.name());
    }

    @Override
    public RemoveResp remove(RemoveReq params) {
        Product existing = productRepository.get(params.id).orElseThrow(NotFoundException::new);
        productRepository.remove(existing);

        return new RemoveResp();
    }

}
