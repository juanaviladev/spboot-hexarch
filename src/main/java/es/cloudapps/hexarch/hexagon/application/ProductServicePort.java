package es.cloudapps.hexarch.hexagon.application;

import java.util.List;

public interface ProductServicePort {

    ProductServicePort.RegisterNewResp registerNew(ProductServicePort.RegisterNewReq params);
    class RegisterNewReq {
        public String name;

        public RegisterNewReq(String name) {
            this.name = name;
        }

        public RegisterNewReq() {
        }
    }
    class RegisterNewResp {
        public Integer id;
        public String name;

        public RegisterNewResp(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public RegisterNewResp() {
        }
    }

    ProductServicePort.RemoveResp remove(ProductServicePort.RemoveReq params);
    class RemoveReq {
        public Integer id;

        public RemoveReq(Integer id) {
            this.id = id;
        }

        public RemoveReq() {
        }
    }
    class RemoveResp {
    }

    ProductServicePort.FindResp find(ProductServicePort.FindReq params);
    class FindReq {
        public Integer id;

        public FindReq(Integer id) {
            this.id = id;
        }

        public FindReq() {
        }
    }
    class FindResp {
        public Integer id;
        public String name;

        public FindResp(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public FindResp() {
        }
    }

    ProductServicePort.FindAllResp findAll(ProductServicePort.FindAllReq params);
    class FindAllReq {
    }
    class FindAllResp {
        public List<ProductDto> items;

        public FindAllResp(List<ProductDto> items) {
            this.items = items;
        }

        public FindAllResp() {
        }
    }
    class ProductDto {
        public Integer id;
        public String name;

        public ProductDto(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public ProductDto() {
        }
    }
}
