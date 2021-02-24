package es.cloudapps.hexarch.hexagon.domain.model;

import es.cloudapps.hexarch.hexagon.domain.exception.NotAvailableProductsException;
import es.cloudapps.hexarch.hexagon.domain.services.CheckoutService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.empty;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartTest {

    @Test
    public void shouldCreateAnEmptyCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertThat(shoppingCart.items(), is(empty()));
    }

    @Test
    public void shouldCreateAnOpenCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertThat(shoppingCart.status(), is(equalTo(ShoppingCart.Status.OPEN)));
    }

    @Test
    public void shouldAddANewItem() {
        ShoppingCart shoppingCart = new ShoppingCart();

        CartItem cartItem = mock(CartItem.class);

        shoppingCart.add(cartItem);

        assertThat(shoppingCart.items(), hasItem(cartItem));
    }

    @Test
    public void shouldRemoveAnExistingItem() {
        ShoppingCart shoppingCart = new ShoppingCart();

        CartItem cartItem = mock(CartItem.class);
        Product product = mock(Product.class);
        when(cartItem.product()).thenReturn(product);

        shoppingCart.add(cartItem);
        shoppingCart.remove(product);

        assertThat(shoppingCart.items(), not(hasItem(cartItem)));
    }

    @Test
    public void shouldReplaceAnExistingItem() {
        ShoppingCart shoppingCart = new ShoppingCart();

        Product product = mock(Product.class);

        CartItem cartItem = mock(CartItem.class);
        when(cartItem.product()).thenReturn(product);
        when(cartItem.quantity()).thenReturn(1);

        CartItem cartItemSubstitute = mock(CartItem.class);
        when(cartItem.product()).thenReturn(product);
        when(cartItem.quantity()).thenReturn(10);

        shoppingCart.add(cartItem);
        shoppingCart.add(cartItemSubstitute);

        assertThat(shoppingCart.items(), hasItem(cartItemSubstitute));
    }

    @Test
    public void shouldUpdateStatusToCompleteWhenCheckoutIsDone() {
        ShoppingCart shoppingCart = new ShoppingCart();

        CheckoutService checkoutService = mock(CheckoutService.class);
        when(checkoutService.validateCart(shoppingCart)).thenReturn(true);

        shoppingCart.checkout(checkoutService);

        assertThat(shoppingCart.status(), is(equalTo(ShoppingCart.Status.COMPLETE)));
    }

    @Test(expected = NotAvailableProductsException.class)
    public void shouldFailWhenCheckoutFails() {
        ShoppingCart shoppingCart = new ShoppingCart();

        CheckoutService checkoutService = mock(CheckoutService.class);
        when(checkoutService.validateCart(shoppingCart)).thenReturn(false);

        shoppingCart.checkout(checkoutService);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenDoingAnyOpAfterCompletion() {
        ShoppingCart shoppingCart = new ShoppingCart();

        CheckoutService checkoutService = mock(CheckoutService.class);
        when(checkoutService.validateCart(shoppingCart)).thenReturn(true);

        CartItem cartItem = mock(CartItem.class);

        shoppingCart.checkout(checkoutService);

        shoppingCart.add(cartItem);
    }
}
