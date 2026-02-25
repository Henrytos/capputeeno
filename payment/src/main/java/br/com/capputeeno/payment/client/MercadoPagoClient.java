package br.com.capputeeno.payment.client;

import br.com.capputeeno.payment.dtos.CreateReferenceRequestDTO;
import br.com.capputeeno.payment.dtos.CreateReferenceResponseDTO;
import br.com.capputeeno.payment.dtos.Payer;
import br.com.capputeeno.payment.dtos.PaymentEntity;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Slf4j
public class MercadoPagoClient {

    @Value("${api.v1.mercadopago-access-token}")
    private String accessToken;

    @Value("${api.v1.mercadopago-notification-url}")
    private String notificationUrl;


    @PostConstruct
    public void init() {
        MercadoPagoConfig.setAccessToken(accessToken);
        log.info("Iniciando MErcado Pago");
    }

    public CreateReferenceResponseDTO createReference(CreateReferenceRequestDTO referenceRequestDTO, String orderNumber) {
        try {


            List<PreferenceItemRequest> items = referenceRequestDTO.items().stream().map(
                    (item) -> {
                        return (PreferenceItemRequest) PreferenceItemRequest.builder()
                                .id(item.id())
                                .title(item.title())
                                .description(item.description())
                                .quantity(item.quantity())
                                .unitPrice(item.unitPrice())
                                .build();
                    }
            ).toList();
            PreferenceBackUrlsRequest backUrls =
                    PreferenceBackUrlsRequest.builder()
                            .success(referenceRequestDTO.backUrls().success())
                            .pending(referenceRequestDTO.backUrls().pending())
                            .failure(referenceRequestDTO.backUrls().failure())
                            .build();


            PreferencePayerRequest payer = PreferencePayerRequest.builder()
                    .name(referenceRequestDTO.payer().name())
                    .email(referenceRequestDTO.payer().email())
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .payer(payer)
                    .autoReturn("approved")
                    .externalReference(orderNumber)
                    .build();

            PreferenceClient client = new PreferenceClient();

            Preference preference = client.create(preferenceRequest);

            return new CreateReferenceResponseDTO(preference.getId(), preference.getInitPoint());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PaymentEntity getPaymentStatus(Long id) throws MPException, MPApiException {
        PaymentClient client = new PaymentClient();

        System.out.println("ID DO PAGAMENTO: "+id);

        log.info("NAO PELO IF E NAO CRIOU O PAYMENT");

        Payment payment = client.get(id);


        if(payment ==null){
            log.error("Payment is null for ID: {}", id);
            throw new RuntimeException("Payment not found for ID: " + id);
        }



        String status = payment.getStatus();

        String paymentMethod = payment.getPaymentMethodId();

        Payer payer = null;

        if(payment.getPayer() != null && payment.getPayer().getIdentification() != null){
            payer = new Payer(
                    payment.getPayer().getEmail(),
                    payment.getPayer().getFirstName(),
                    payment.getPayer().getLastName(),
                    new Payer.Identification(
                            payment.getPayer().getIdentification().getType(),
                            payment.getPayer().getIdentification().getNumber()
                    )
                );
        }

        log.info("PASSOU PELO IF E CRIOU O PAYMENT");

        return new PaymentEntity(
                payment.getId().toString(),
                status,
                payment.getExternalReference(),
                payer,
                paymentMethod,
                payment.getStatusDetail()
        );
    }

}
