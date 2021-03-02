package es.cloudapps.hexarch.hexagon.application;

import java.util.List;

public interface ShoppingCartQueryServicePort {

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

}



