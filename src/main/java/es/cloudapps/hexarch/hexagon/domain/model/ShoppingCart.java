package es.cloudapps.hexarch.hexagon.domain.model;

import es.cloudapps.hexarch.hexagon.domain.exception.NotAvailableProductsException;
import es.cloudapps.hexarch.hexagon.domain.services.CheckoutService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {

    private Integer id;
    private final List<CartItem> items;
    private Status status;
    private Integer totalQuantity;

    public ShoppingCart() {
        this.status = Status.OPEN;
        this.items = new ArrayList<>();
        this.totalQuantity = 0;
    }

    protected ShoppingCart(Integer id, List<CartItem> items, Status status, Integer totalQuantity) {
        this.id = id;
        this.items = items;
        this.status = status;
        this.totalQuantity = totalQuantity;
    }

    public void add(CartItem item) {
        this.checkStatus();
        if (this.items.remove(item))
            totalQuantity -= item.quantity();
        this.items.add(item);
        this.totalQuantity += item.quantity();
    }

    public void remove(Product product) {
        this.checkStatus();
        Optional<CartItem> cartItem = this.items
                .stream()
                .filter(item -> item.product().equals(product))
                .findFirst();
        if (cartItem.isPresent()) {
            this.totalQuantity -= cartItem.get().quantity();
            this.items.removeIf(item -> item.product().equals(product));
        }
    }

    public void checkout(CheckoutService checkoutService) {
        this.checkStatus();
        boolean isValid = checkoutService.validateCart(this);
        if (!isValid) throw new NotAvailableProductsException();

        this.status = Status.COMPLETE;
    }

    private void checkStatus() {
        if (status != Status.OPEN) throw new IllegalStateException("Operation not allowed, checkout already performed");
    }

    public List<CartItem> items() {
        return new ArrayList<>(items);
    }

    public Integer id() {
        return id;
    }

    public Status status() {
        return status;
    }

    public Integer totalQuantity() {
        return totalQuantity;
    }

    public enum Status {
        COMPLETE,
        OPEN
    }
}
