package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class JpaProduct {
    @Id
    @GeneratedValue
    public Integer id;

    public String name;
}
