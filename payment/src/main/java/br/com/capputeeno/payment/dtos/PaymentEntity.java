package br.com.capputeeno.payment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor 
@Data
public class PaymentEntity {
    
    private String id;
    private String status;
    private String orderId;
    private Payer payer;
    private String paymentMethod;
    private String statusDetail;
}
