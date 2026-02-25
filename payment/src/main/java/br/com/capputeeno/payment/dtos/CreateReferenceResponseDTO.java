package br.com.capputeeno.payment.dtos;

public record CreateReferenceResponseDTO(
        String preferenceId,
        String redirectUrl
) {
}
