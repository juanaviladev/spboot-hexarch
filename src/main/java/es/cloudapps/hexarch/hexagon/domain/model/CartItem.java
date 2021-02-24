package es.cloudapps.hexarch.hexagon.domain.model;

import java.util.Objects;

public class CartItem {

    private Product product;
    private Integer quantity;

    public CartItem(Product product, Integer quantity) {
        this.checkInvariants(product, quantity);
        this.product = product;
        this.quantity = quantity;
    }

    private void checkInvariants(Product product, Integer quantity) {
        if(product == null) throw new IllegalArgumentException("Product cannot be null");
        if(quantity == null) throw new IllegalArgumentException("Product quantity cannot be null");
        if(quantity <= 0) throw new IllegalArgumentException("Product quantity cannot be negative or zero");
    }

    public Product product() {
        return product;
    }

    public Integer quantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
