package es.cloudapps.hexarch.hexagon.application;

import java.util.List;

public interface ProductQueryServicePort {

    FindResp find(FindReq params);
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

    FindAllResp findAll(FindAllReq params);
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
