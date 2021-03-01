package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa;

import es.cloudapps.hexarch.hexagon.domain.model.Product;
import es.cloudapps.hexarch.hexagon.domain.services.ProductRepository;
import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.SpringProductRepository;
import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.FillableProduct;
import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.JpaProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Repository
public class JpaProductRepository implements ProductRepository {

    private final SpringProductRepository springProductRepository;

    public JpaProductRepository(SpringProductRepository springProductRepository) {
        this.springProductRepository = springProductRepository;
    }

    @Override
    public Product save(Product product) {
        Integer productId = product.id();
        JpaProduct storedProduct;

        if (productId == null) storedProduct = springProductRepository.save(new JpaProduct());
        else storedProduct = springProductRepository.findById(productId).orElseThrow(IllegalStateException::new);

        storedProduct.name = product.name();

        springProductRepository.save(storedProduct);

        return new FillableProduct(storedProduct.id, new Product.Name(product.name()), storedProduct.cost);
    }

    @Override
    public void remove(Product product) {
        springProductRepository.deleteById(product.id());
    }

    @Override
    public Optional<Product> get(Integer productId) {
        Optional<JpaProduct> storedProduct = springProductRepository.findById(productId);
        return storedProduct.map(stored ->
                new FillableProduct(productId, new Product.Name(stored.name), stored.cost));
    }

    @Override
    public List<Product> all() {
        return stream(springProductRepository.findAll().spliterator(),false)
                .map(jpaProduct -> new FillableProduct(jpaProduct.id, new Product.Name(jpaProduct.name), jpaProduct.cost))
                .collect(Collectors.toList());

    }
}
