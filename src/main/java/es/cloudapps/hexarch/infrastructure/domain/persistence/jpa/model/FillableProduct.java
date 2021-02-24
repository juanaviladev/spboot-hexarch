package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model;

import es.cloudapps.hexarch.hexagon.domain.model.Product;

public class FillableProduct extends Product {

    public FillableProduct(Integer id, Name name) {
        super(id, name);
    }
}
