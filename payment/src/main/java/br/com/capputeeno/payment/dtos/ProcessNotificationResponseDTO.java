package br.com.capputeeno.payment.dtos;

public record ProcessNotificationResponseDTO(
    boolean success,
    String updatedStatus
) {

}
