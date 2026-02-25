package br.com.capputeeno.payment.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CreateReferenceRequestDTO(

        Long userId,
        @NotNull
        BigDecimal totalAmount,
        @NotNull
        PayerDTO payer,
        @NotNull
        BackUrlsDTO backUrls,
        @NotNull
        DeliveryAddressDTO deliveryAddress,
        @NotNull
        String notificationUrl,
        @NotNull
        List<ItemDTO> items
) {

    public record PayerDTO(
            String name,
            String email
    ) {
    }

    public record BackUrlsDTO(
            String success,
            String pending,
            String failure
    ) {
    }

    public record DeliveryAddressDTO(
            String zipCode,
            String street,
            String number,
            String complement,
            String city,
            String state,
            String neighborhood
    ) {
    }

    public record ItemDTO(
        String id,
        String title,
        String description,
        Integer quantity,
        BigDecimal unitPrice
    ) {
    }
}


