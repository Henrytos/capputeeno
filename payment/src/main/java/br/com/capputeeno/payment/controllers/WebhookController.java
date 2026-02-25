package br.com.capputeeno.payment.controllers;

import br.com.capputeeno.payment.dtos.MercadoPagoConfigDTO;
import br.com.capputeeno.payment.services.ProcessPaymentNotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/webhoks")
@RequiredArgsConstructor
@Slf4j
public class WebhookController {

    private final ProcessPaymentNotificationService processPaymentNotificationService;
    

    @PostMapping("mercadopago")
    public ResponseEntity<Void> processNotification(
            @Valid @RequestBody MercadoPagoConfigDTO request
            ){

        try{
            System.out.println(request);
            processPaymentNotificationService.processNotification(request.data().id(), request.type());

            log.info("Received MercadoPago webhook: {}", request);
        }
        catch (Exception e){
            log.error("Error processing MercadoPago webhook: {}", e.getMessage());
        }


        return ResponseEntity.ok().build();
    }

}
