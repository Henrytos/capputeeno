package br.com.capputeeno.auth.application.port.dtos;

public record SignInUserResponseDTO(
        String token,
        String refreshToken
        ) {
}
