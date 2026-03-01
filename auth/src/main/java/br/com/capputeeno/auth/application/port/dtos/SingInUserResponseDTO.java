package br.com.capputeeno.auth.application.port.dtos;

public record SingInUserResponseDTO(
        String token,
        String refreshToken
        ) {
}
