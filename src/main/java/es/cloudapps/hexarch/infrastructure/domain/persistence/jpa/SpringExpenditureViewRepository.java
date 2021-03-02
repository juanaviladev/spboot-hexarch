package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa;

import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.JpaExpenditureView;
import org.springframework.data.repository.CrudRepository;

public interface SpringExpenditureViewRepository extends CrudRepository<JpaExpenditureView, Integer> {
}
