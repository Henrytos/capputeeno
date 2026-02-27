package br.com.capputeeno.auth.domain.entites;

public record CreateUserRequestDTO(
        String username,
        String email,
        String password,
        String imageUrl
) {

}
