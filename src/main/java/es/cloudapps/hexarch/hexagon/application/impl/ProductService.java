package es.cloudapps.hexarch.hexagon.application.impl;

import es.cloudapps.hexarch.hexagon.application.ProductServicePort;
import es.cloudapps.hexarch.hexagon.domain.exception.NotFoundException;
import es.cloudapps.hexarch.hexagon.domain.model.Product;
import es.cloudapps.hexarch.hexagon.domain.services.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements ProductServicePort {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public RegisterNewResp registerNew(RegisterNewReq params) {
        Product product = new Product(new Product.Name(params.name));
        Product saved = productRepository.save(product);
        return new RegisterNewResp(saved.id(), saved.name());
    }

    @Override
    public RemoveResp remove(RemoveReq params) {
        Product existing = productRepository.get(params.id).orElseThrow(NotFoundException::new);
        productRepository.remove(existing);

        return new RemoveResp();
    }

    @Override
    public FindResp find(FindReq params) {
        Product existing = productRepository.get(params.id).orElseThrow(NotFoundException::new);
        return new FindResp(existing.id(), existing.name());
    }

    @Override
    public FindAllResp findAll(FindAllReq params) {
        return new FindAllResp(map(productRepository.all()));
    }

    private List<ProductDto> map(List<Product> all) {
        return all.stream()
                .map(product -> new ProductDto(product.id(), product.name()))
                .collect(Collectors.toList());
    }
}
