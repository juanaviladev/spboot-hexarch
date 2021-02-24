package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class JpaCartItem {

    @Id
    @GeneratedValue
    public Integer id;

    @ManyToOne
    public JpaProduct product;

    @ManyToOne
    public JpaShoppingCart cart;

    public Integer quantity;
}
