package es.cloudapps.hexarch.hexagon.application;

import java.io.Serializable;

public interface ProductCommandServicePort {

    RegisterNewResp registerNew(RegisterNewReq params);
    class RegisterNewReq implements Serializable {
        public String name;
        public Integer cost;

        public RegisterNewReq(String name, Integer cost) {
            this.name = name;
            this.cost = cost;
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

    RemoveResp remove(RemoveReq params);
    class RemoveReq implements Serializable {
        public Integer id;

        public RemoveReq(Integer id) {
            this.id = id;
        }

        public RemoveReq() {
        }
    }
    class RemoveResp {
    }

}
