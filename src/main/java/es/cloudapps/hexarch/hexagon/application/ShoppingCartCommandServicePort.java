package es.cloudapps.hexarch.hexagon.application;

import es.cloudapps.hexarch.hexagon.domain.exception.NotAvailableProductsException;

import java.io.Serializable;
import java.util.List;

public interface ShoppingCartCommandServicePort {

    RegisterNewCartResp registerNewCart(RegisterNewCartReq params);
    class RegisterNewCartReq implements Serializable {
        public List<CartItemDto> items;

        public RegisterNewCartReq(List<CartItemDto> items) {
            this.items = items;
        }

        public RegisterNewCartReq() {
        }
    }
    class RegisterNewCartResp {
        public Integer id;
        public List<CartItemDto> items;

        public RegisterNewCartResp(Integer id, List<CartItemDto> items) {
            this.id = id;
            this.items = items;
        }

        public RegisterNewCartResp() {
        }
    }
    class CartItemDto {
        public Integer id;
        public Integer quantity;

        public CartItemDto(Integer id, Integer quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        public CartItemDto() {
        }
    }

    CheckoutCartResp checkoutCart(CheckoutCartReq params) throws NotAvailableProductsException;
    class CheckoutCartReq implements Serializable {
        public Integer id;

        public CheckoutCartReq(Integer id) {
            this.id = id;
        }

        public CheckoutCartReq() {
        }
    }
    class CheckoutCartResp {
        public Integer id;
        public String status;

        public CheckoutCartResp(Integer id, String status) {
            this.id = id;
            this.status = status;
        }

        public CheckoutCartResp() {
        }
    }

    RemoveCartResp removeCart(RemoveCartReq params);
    class RemoveCartReq implements Serializable {
        public Integer id;

        public RemoveCartReq(Integer id) {
            this.id = id;
        }

        public RemoveCartReq() {
        }
    }
    class RemoveCartResp {
    }

    AddToCartResp addToCart(AddToCartReq params);
    class AddToCartReq implements Serializable {
        public Integer cartId;
        public Integer productId;
        public Integer quantity;

        public AddToCartReq(Integer cartId, Integer productId, Integer quantity) {
            this.cartId = cartId;
            this.productId = productId;
            this.quantity = quantity;
        }

        public AddToCartReq() {
        }
    }
    class AddToCartResp {

    }

    RemoveFromCartResp removeFromCart(RemoveFromCartReq params);
    class RemoveFromCartReq implements Serializable {
        public Integer cartId;
        public Integer productId;

        public RemoveFromCartReq(Integer cartId, Integer productId) {
            this.cartId = cartId;
            this.productId = productId;
        }

        public RemoveFromCartReq() {
        }
    }
    class RemoveFromCartResp {
    }

}
