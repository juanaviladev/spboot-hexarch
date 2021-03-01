package es.cloudapps.hexarch.hexagon.domain.model;

import org.junit.Test;

public class ProductTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameIsNull() {
        new Product(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameIsEmpty() {
        new Product(new Product.Name("     "), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameHasNullValue() {
        new Product(new Product.Name(null), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCostHasNullValue() {
        new Product(new Product.Name(null), null);
    }

    @Test
    public void shouldCreateAProductWhenNameIsFilled() {
        new Product(new Product.Name("OK"), 1);
    }

}
