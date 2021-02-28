package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class JpaShoppingCart {
    @Id
    @GeneratedValue
    public Integer id;

    @Enumerated
    public JpaCartStatus status;

    @Column
    public Integer totalQuantity;
}
