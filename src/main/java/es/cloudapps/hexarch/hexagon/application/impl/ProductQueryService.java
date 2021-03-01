package es.cloudapps.hexarch.hexagon.application.impl;

import es.cloudapps.hexarch.hexagon.application.ProductQueryServicePort;
import es.cloudapps.hexarch.hexagon.domain.exception.NotFoundException;
import es.cloudapps.hexarch.hexagon.domain.model.Product;
import es.cloudapps.hexarch.hexagon.domain.services.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductQueryService implements ProductQueryServicePort {

    private final ProductRepository productRepository;

    public ProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
