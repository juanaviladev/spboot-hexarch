package es.cloudapps.hexarch.hexagon.application.impl;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartServicePort.*;
import es.cloudapps.hexarch.hexagon.domain.model.CartItem;
import es.cloudapps.hexarch.hexagon.domain.model.Product;
import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;
import es.cloudapps.hexarch.hexagon.domain.services.CheckoutService;
import es.cloudapps.hexarch.hexagon.domain.services.ProductRepository;
import es.cloudapps.hexarch.hexagon.domain.services.ShoppingCartRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartServiceTest {

    private ShoppingCartRepository shoppingCartRepository;

    private ProductRepository productRepository;

    private CheckoutService checkoutService;

    private ShoppingCartService shoppingCartService;

    @Before
    public void setUp() {
        this.shoppingCartRepository = mock(ShoppingCartRepository.class);
        this.productRepository = mock(ProductRepository.class);
        this.checkoutService = mock(CheckoutService.class);

        this.shoppingCartService = new ShoppingCartService(
                shoppingCartRepository,
                productRepository,
                checkoutService
        );
    }

    @Test
    public void shouldDoCheckoutWhenCartIsOpen() {
        CheckoutCartReq req = new CheckoutCartReq();
        req.id = 1;

        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.id()).thenReturn(1);
        when(shoppingCart.status()).thenReturn(ShoppingCart.Status.COMPLETE);

        when(shoppingCartRepository.get(1)).thenReturn(Optional.of(shoppingCart));

        when(checkoutService.validateCart(any())).thenReturn(true);
        CheckoutCartResp resp = shoppingCartService.checkoutCart(req);

        assertThat(resp.status, is(equalTo("COMPLETE")));
    }

    @Test
    public void shouldReturnAnExistingCart() {
        CartItem item1 = itemWithIdAndQty(1, 10);
        CartItem item2 = itemWithIdAndQty(1, 20);
        CartItem item3 = itemWithIdAndQty(1, 30);

        ShoppingCart cart = mock(ShoppingCart.class);
        when(cart.items()).thenReturn(asList(item1, item2, item3));
        when(cart.status()).thenReturn(ShoppingCart.Status.OPEN);
        when(cart.id()).thenReturn(1);

        when(shoppingCartRepository.get(1)).thenReturn(Optional.of(cart));

        GetCartReq req = new GetCartReq();
        req.id = 1;

        GetCartResp resp = shoppingCartService.getCart(req);

        assertThat(resp.items, hasSize(3));
    }

    private static CartItemDto dtoItemWithIdAndQty(int id, int qty) {
        return new CartItemDto(id, qty);
    }

    private static CartItem itemWithIdAndQty(int id, int qty) {
        CartItem cartItem = mock(CartItem.class);
        Product product = mock(Product.class);
        when(product.id()).thenReturn(id);
        when(cartItem.quantity()).thenReturn(qty);
        when(cartItem.product()).thenReturn(product);
        return cartItem;
    }

}
