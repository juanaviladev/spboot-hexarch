package es.cloudapps.hexarch.hexagon.domain.model;

import es.cloudapps.hexarch.hexagon.domain.exception.NotAvailableProductsException;
import es.cloudapps.hexarch.hexagon.domain.services.CheckoutService;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private Integer id;
    private final List<CartItem> items;
    private Status status;

    public ShoppingCart() {
        this.status = Status.OPEN;
        this.items = new ArrayList<>();
    }

    protected ShoppingCart(Integer id, List<CartItem> items, Status status) {
        this.id = id;
        this.items = items;
        this.status = status;
    }

    public void add(CartItem item) {
        this.checkStatus();
        this.items.remove(item);
        this.items.add(item);
    }

    public void remove(Product product) {
        this.checkStatus();
        this.items.removeIf(item -> item.product().equals(product));
    }

    public void checkout(CheckoutService checkoutService) {
        this.checkStatus();
        boolean isValid = checkoutService.validateCart(this);
        if(!isValid) throw new NotAvailableProductsException();

        this.status = Status.COMPLETE;
    }

    private void checkStatus() {
        if(status != Status.OPEN) throw new IllegalStateException("Operation not allowed, checkout already performed");
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

    public enum Status {
        COMPLETE,
        OPEN
    }
}
