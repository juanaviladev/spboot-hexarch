package es.cloudapps.hexarch.hexagon.domain.model;

import java.util.Objects;

public class Product {

    private Integer id;
    private Name name;
    private Integer cost;

    public Product(Name name, Integer cost) {
        this.cost = cost;
        this.name = name;
        this.checkInvariants(name);
    }

    private void checkInvariants(Name name) {
        if(name == null) throw new IllegalArgumentException("Product name must be not null");
        if(cost == null) throw new IllegalArgumentException("Product cost must be not null");
    }

    protected Product(Integer id, Name name, Integer cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        return name.value;
    }

    public Integer cost() {
        return this.cost;
    }

    public static class Name {
        private final String value;

        public Name(String value) {
            this.checkInvariants(value);
            this.value = value;
        }

        private void checkInvariants(String value) {
            if(value == null) throw new IllegalArgumentException("Product name must be not null");
            if(value.trim().isEmpty()) throw new IllegalArgumentException("Product name must be not empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
