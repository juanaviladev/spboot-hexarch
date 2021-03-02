package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa;

import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.JpaCartStatus;
import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.JpaShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringShoppingCartRepository extends CrudRepository<JpaShoppingCart, Integer> {

    List<JpaShoppingCart> findAllByStatus(JpaCartStatus status);

}
