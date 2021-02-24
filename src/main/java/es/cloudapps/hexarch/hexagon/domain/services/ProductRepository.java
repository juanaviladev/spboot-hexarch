package es.cloudapps.hexarch.hexagon.domain.services;

import es.cloudapps.hexarch.hexagon.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    void remove(Product product);
    Optional<Product> get(Integer id);
    List<Product> all();
}
