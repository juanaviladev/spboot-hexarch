package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa;

import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.JpaShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface SpringShoppingCartRepository extends CrudRepository<JpaShoppingCart, Integer> {
}
