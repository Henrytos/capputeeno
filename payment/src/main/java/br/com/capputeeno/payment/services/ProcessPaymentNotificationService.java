package br.com.capputeeno.payment.services;

import org.springframework.stereotype.Service;

import br.com.capputeeno.payment.client.MercadoPagoClient;
import br.com.capputeeno.payment.dtos.PaymentEntity;
import br.com.capputeeno.payment.dtos.ProcessNotificationResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessPaymentNotificationService {

    private final MercadoPagoClient mercadoPagoClient;


    public ProcessNotificationResponseDTO processNotification(String id, String type){

        try {
            PaymentEntity payment = mercadoPagoClient.getPaymentStatus(Long.parseLong(id)); 

            // Aqui você pode implementar a lógica para atualizar o status do pedido com base no status do pagamento
            log.info("Payment status for ID {}: {}", id, payment.getStatus());

            return new ProcessNotificationResponseDTO(
                true,
                payment.getStatus()
            );
        } catch (Exception e) {
            log.error("Error processing notification", e);
        
            return new ProcessNotificationResponseDTO(
                false,
                "Error processing notification: " + e.getMessage()
            );
        }


    }

}
