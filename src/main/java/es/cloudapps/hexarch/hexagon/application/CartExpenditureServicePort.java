package es.cloudapps.hexarch.hexagon.application;

import java.util.List;

public interface CartExpenditureServicePort {

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
