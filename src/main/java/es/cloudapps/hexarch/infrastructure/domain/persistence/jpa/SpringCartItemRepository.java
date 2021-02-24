package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa;

import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.JpaCartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringCartItemRepository extends CrudRepository<JpaCartItem, Integer> {
    void deleteByCartId(Integer cartId);

    List<JpaCartItem> findAllByCartId(Integer cartId);

}
