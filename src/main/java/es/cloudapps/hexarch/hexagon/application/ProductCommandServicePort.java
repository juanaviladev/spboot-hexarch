package es.cloudapps.hexarch.hexagon.application;

public interface ProductCommandServicePort {

    RegisterNewResp registerNew(RegisterNewReq params);
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

    RemoveResp remove(RemoveReq params);
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

}
