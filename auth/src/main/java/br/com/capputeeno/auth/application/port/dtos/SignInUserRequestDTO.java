package br.com.capputeeno.auth.application.port.dtos;

public record SignInUserRequestDTO(
        String email,
        String password
) {
}
