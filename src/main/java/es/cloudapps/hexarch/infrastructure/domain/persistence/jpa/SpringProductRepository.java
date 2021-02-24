package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa;

import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.JpaProduct;
import org.springframework.data.repository.CrudRepository;

public interface SpringProductRepository extends CrudRepository<JpaProduct, Integer> {
}
