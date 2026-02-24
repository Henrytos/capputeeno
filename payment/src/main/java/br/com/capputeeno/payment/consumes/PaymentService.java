package br.com.capputeeno.payment.consumes;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @KafkaListener(topics = "PAYMENT_REQUEST", groupId = "PaymentService")
    public void requestPayment(String message, Acknowledgment acknowledgment){
        System.out.println(message);

        acknowledgment.acknowledge();
    }

}
