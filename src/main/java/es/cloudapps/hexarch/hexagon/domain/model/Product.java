package es.cloudapps.hexarch.hexagon.domain.model;

import java.util.Objects;

public class Product {

    private Integer id;
    private Name name;

    public Product(Name name) {
        this.checkInvariants(name);
        this.name = name;
    }

    private void checkInvariants(Name name) {
        if(name == null) throw new IllegalArgumentException("Product name must be not null");
    }

    protected Product(Integer id, Name name) {
        this.id = id;
        this.name = name;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        return name.value;
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
