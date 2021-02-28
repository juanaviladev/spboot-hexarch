package es.cloudapps.hexarch.infrastructure.domain.persistence.jpa;

import es.cloudapps.hexarch.hexagon.domain.model.CartItem;
import es.cloudapps.hexarch.hexagon.domain.model.Product;
import es.cloudapps.hexarch.hexagon.domain.model.Product.Name;
import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;
import es.cloudapps.hexarch.hexagon.domain.services.ShoppingCartRepository;
import es.cloudapps.hexarch.infrastructure.domain.persistence.jpa.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaShoppingCartRepository implements ShoppingCartRepository {

    private final SpringShoppingCartRepository shoppingCartRepository;
    private final SpringCartItemRepository springCartItemRepository;

    public JpaShoppingCartRepository(SpringShoppingCartRepository shoppingCartRepository, SpringCartItemRepository springCartItemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.springCartItemRepository = springCartItemRepository;
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        Integer cartId = cart.id();
        JpaShoppingCart storedCart;

        if (cartId == null) storedCart = shoppingCartRepository.save(new JpaShoppingCart());
        else storedCart = shoppingCartRepository.findById(cartId).orElseThrow(IllegalStateException::new);

        storedCart.status = map(cart.status());
        storedCart.totalQuantity = cart.totalQuantity();

        springCartItemRepository.deleteByCartId(storedCart.id);

        cart.items().forEach(cartItem -> {
            JpaCartItem jpaCartItem = new JpaCartItem();
            jpaCartItem.cart = storedCart;
            jpaCartItem.quantity = cartItem.quantity();
            jpaCartItem.product = map(cartItem.product());

            springCartItemRepository.save(jpaCartItem);
        });

        return new FillableShoppingCart(storedCart.id, cart.items(), cart.status(), cart.totalQuantity());
    }

    private JpaProduct map(Product product) {
        JpaProduct jpaProduct = new JpaProduct();
        jpaProduct.id = product.id();
        jpaProduct.name = product.name();
        return jpaProduct;
    }

    @Override
    public void remove(ShoppingCart cart) {
        Integer cartId = cart.id();
        JpaShoppingCart storedCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(IllegalStateException::new);

        springCartItemRepository.deleteByCartId(cartId);
        shoppingCartRepository.delete(storedCart);
    }

    @Override
    public Optional<ShoppingCart> get(Integer cartId) {
        Optional<JpaShoppingCart> cart = shoppingCartRepository.findById(cartId);

        List<CartItem> cartItems = springCartItemRepository.findAllByCartId(cartId)
                .stream()
                .map(item -> new CartItem(map(item.product), item.quantity))
                .collect(Collectors.toList());

        return cart.map(opt -> new FillableShoppingCart(
                cartId, cartItems, map(opt.status), opt.totalQuantity
        ));
    }

    private ShoppingCart.Status map(JpaCartStatus status) {
        switch (status) {
            case COMPLETE:
                return ShoppingCart.Status.COMPLETE;
            case OPEN:
                return ShoppingCart.Status.OPEN;
        }
        throw new IllegalStateException();
    }

    private JpaCartStatus map(ShoppingCart.Status status) {
        switch (status) {
            case COMPLETE:
                return JpaCartStatus.COMPLETE;
            case OPEN:
                return JpaCartStatus.OPEN;
        }
        throw new IllegalStateException();
    }

    private Product map(JpaProduct product) {
        return new FillableProduct(product.id, new Name(product.name));
    }

    @Override
    public List<ShoppingCart> getAllCompleted() {
        List<JpaShoppingCart> cartList = shoppingCartRepository.findAllByStatus(JpaCartStatus.COMPLETE);
        return cartList.stream()
                .map(cart -> new FillableShoppingCart(cart.id, null, map(cart.status), cart.totalQuantity))
                .collect(Collectors.toList());
    }


}
