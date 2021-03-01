package es.cloudapps.hexarch.hexagon.application.impl;

import es.cloudapps.hexarch.hexagon.application.ShoppingCartServicePort;
import es.cloudapps.hexarch.hexagon.domain.exception.NotAvailableProductsException;
import es.cloudapps.hexarch.hexagon.domain.exception.NotFoundException;
import es.cloudapps.hexarch.hexagon.domain.model.CartItem;
import es.cloudapps.hexarch.hexagon.domain.model.Product;
import es.cloudapps.hexarch.hexagon.domain.model.ShoppingCart;
import es.cloudapps.hexarch.hexagon.domain.services.CheckoutService;
import es.cloudapps.hexarch.hexagon.domain.services.ProductRepository;
import es.cloudapps.hexarch.hexagon.domain.services.ShoppingCartRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartService implements ShoppingCartServicePort {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final CheckoutService checkoutService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, CheckoutService checkoutService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.checkoutService = checkoutService;
    }

    @Override
    public RegisterNewCartResp registerNewCart(RegisterNewCartReq params) {
        ShoppingCart shoppingCart = new ShoppingCart();

        params.items.stream()
                .map(this::map)
                .forEach(shoppingCart::add);
        params.items.stream().forEach(item -> {
            shoppingCart.addToTotalQuantity(item.quantity);
        });

        ShoppingCart saved = shoppingCartRepository.save(shoppingCart);
        return new RegisterNewCartResp(saved.id(), params.items);
    }

    private CartItem map(CartItemDto cartItemDto) {
        Product product = productRepository.get(cartItemDto.id).orElseThrow(NotFoundException::new);
        return new CartItem(product, cartItemDto.quantity);
    }

    @Override
    public CheckoutCartResp checkoutCart(CheckoutCartReq params) throws NotAvailableProductsException {
        ShoppingCart shoppingCart = shoppingCartRepository.get(params.id)
                .orElseThrow(IllegalArgumentException::new);

        shoppingCart.checkout(checkoutService);
        shoppingCartRepository.save(shoppingCart);

        return new CheckoutCartResp(shoppingCart.id(), shoppingCart.status().toString());
    }

    @Override
    public GetCartResp getCart(GetCartReq params) {
        ShoppingCart shoppingCart = shoppingCartRepository.get(params.id)
                .orElseThrow(NotFoundException::new);

        return new GetCartResp(shoppingCart.id(), map(shoppingCart.items()), shoppingCart.status().toString(), shoppingCart.totalQuantity());
    }

    private List<CartItemDto> map(List<CartItem> items) {
        return items.stream().map(item -> new CartItemDto(item.product().id(), item.quantity()))
                .collect(Collectors.toList());
    }

    @Override
    public RemoveCartResp removeCart(RemoveCartReq params) {
        ShoppingCart shoppingCart = shoppingCartRepository.get(params.id)
                .orElseThrow(NotFoundException::new);

        shoppingCartRepository.remove(shoppingCart);

        return new RemoveCartResp();
    }

    @Override
    public AddToCartResp addToCart(AddToCartReq params) {
        Product product = productRepository.get(params.productId).orElseThrow(NotFoundException::new);
        ShoppingCart shoppingCart = shoppingCartRepository.get(params.cartId).orElseThrow(NotFoundException::new);

        shoppingCart.add(new CartItem(product, params.quantity));
        shoppingCartRepository.save(shoppingCart);

        return new AddToCartResp();
    }

    @Override
    public RemoveFromCartResp removeFromCart(RemoveFromCartReq params) {
        Product product = productRepository.get(params.productId).orElseThrow(NotFoundException::new);
        ShoppingCart shoppingCart = shoppingCartRepository.get(params.cartId).orElseThrow(NotFoundException::new);

        shoppingCart.remove(product);
        shoppingCartRepository.save(shoppingCart);

        return new RemoveFromCartResp();
    }

}
