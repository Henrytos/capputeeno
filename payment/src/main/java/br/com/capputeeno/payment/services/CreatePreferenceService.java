package br.com.capputeeno.payment.services;

import br.com.capputeeno.payment.client.MercadoPagoClient;
import br.com.capputeeno.payment.dtos.CreateReferenceRequestDTO;
import br.com.capputeeno.payment.dtos.CreateReferenceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePreferenceService {

    private final MercadoPagoClient mercadoPagoClient;

    public CreateReferenceResponseDTO execute(
            CreateReferenceRequestDTO request
    ) {
        String orderNumber = String.valueOf(Math.random() * 2000 + 100);

        // regras de ngc , validar ususairo e pedido

        return this.mercadoPagoClient.createReference(request, orderNumber);
    }

}
