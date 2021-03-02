package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Access(AccessType.FIELD)
public class JpaExpenditureView {

    @Id
    @GeneratedValue
    public Integer id;

    public Integer cartId;
    public Integer quantity;

}
