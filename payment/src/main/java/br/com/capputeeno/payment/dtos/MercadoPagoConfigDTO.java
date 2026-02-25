package br.com.capputeeno.payment.dtos;


public record MercadoPagoConfigDTO(
        String action,
        String api_version,
        Data data,
        String date_created,
        String id,
        String live_mode,
        String type,
        Long user_id

) {

        public record Data(
                   String id
        ) {
        
        }

        @Override
        public String toString() {
                return "MercadoPagoConfigDTO{" +
                        "action='" + action + '\'' +
                        ", api_version='" + api_version + '\'' +
                        ", data=" + data +
                        ", date_created='" + date_created + '\'' +
                        ", id='" + id + '\'' +
                        ", live_mode='" + live_mode + '\'' +
                        ", type='" + type + '\'' +
                        ", user_id=" + user_id +
                        '}';
        }
}


//{
//
//action: "payment.updated",
//
//api_version: "v1",
//
//data: {"id":"123456"},
//
//date_created: "2021-11-01T02:02:02Z",
//
//id: "123456",
//
//live_mode: false,
//
//type: "payment",
//
//user_id: 482627253
//
//        }