package es.cloudapps.hexarch.hexagon.application;

import es.cloudapps.hexarch.hexagon.domain.exception.NotAvailableProductsException;

import java.util.List;

public interface ShoppingCartServicePort {

    RegisterNewCartResp registerNewCart(RegisterNewCartReq params);
    class RegisterNewCartReq {
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
    class CheckoutCartReq {
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

    GetCartResp getCart(GetCartReq params);
    class GetCartReq {
        public Integer id;

        public GetCartReq(Integer id) {
            this.id = id;
        }

        public GetCartReq() {
        }
    }
    class GetCartResp {
        public Integer id;
        public List<CartItemDto> items;
        public String status;
        public Integer totalQuantity;

        public GetCartResp(Integer id, List<CartItemDto> items, String status, Integer totalQuantity) {
            this.id = id;
            this.items = items;
            this.status = status;
            this.totalQuantity = totalQuantity;
        }

        public GetCartResp() {
        }
    }

    RemoveCartResp removeCart(RemoveCartReq params);
    class RemoveCartReq {
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
    class AddToCartReq {
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
    class RemoveFromCartReq {
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

    CartExpenditureResp getCartExpenditure(CartExpenditureReq params);
    class CartExpenditureReq {
    }
    class CartExpenditureResp {
       public List<ExpenditureDto> items;

        public CartExpenditureResp(List<ExpenditureDto> items) {
            this.items = items;
        }

        public CartExpenditureResp() {
        }
    }
    class ExpenditureDto {
        public Integer cartId;
        public Integer expenditure;

        public ExpenditureDto(Integer cartId, Integer expenditure) {
            this.cartId = cartId;
            this.expenditure = expenditure;
        }

        public ExpenditureDto() {
        }
    }

}



