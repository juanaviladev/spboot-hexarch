package es.cloudapps.hexarch.hexagon.domain.model;

import org.junit.Test;

public class ProductTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameIsNull() {
        new Product(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameIsEmpty() {
        new Product(new Product.Name("     "));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameHasNullValue() {
        new Product(new Product.Name(null));
    }

    @Test
    public void shouldCreateAProductWhenNameIsFilled() {
        new Product(new Product.Name("OK"));
    }

}
