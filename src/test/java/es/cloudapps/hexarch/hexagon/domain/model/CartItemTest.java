package es.cloudapps.hexarch.hexagon.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CartItemTest {

    private static final Integer OK_QUANTITY = 1;

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenQuantityIsZero() {
        Product product = mock(Product.class);
        new CartItem(product,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenQuantityIsBelowZero() {
        Product product = mock(Product.class);
        new CartItem(product,-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenProductIsNull() {
        Product product = null;
        new CartItem(product,OK_QUANTITY);
    }

    @Test
    public void shouldReturnTrueWhenTwoCartItemHaveEqualProducts() {
        Product product = mock(Product.class);
        when(product.id()).thenReturn(1);

        CartItem one = new CartItem(product,OK_QUANTITY);
        CartItem two = new CartItem(product, OK_QUANTITY);

        assertEquals(one,two);
    }
}
