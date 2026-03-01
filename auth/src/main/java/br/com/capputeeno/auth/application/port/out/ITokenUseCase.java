package br.com.capputeeno.auth.application.port.out;

import br.com.capputeeno.auth.application.port.dtos.SignInUserResponseDTO;
import br.com.capputeeno.auth.domain.entites.UserEntity;

public interface ITokenUseCase {

    SignInUserResponseDTO generate(UserEntity user);

    boolean validate(String token);

    String getSubject(String token);

}
