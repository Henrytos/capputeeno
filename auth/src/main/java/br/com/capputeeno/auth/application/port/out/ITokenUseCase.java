package br.com.capputeeno.auth.application.port.out;

import br.com.capputeeno.auth.application.port.dtos.SingInUserResponseDTO;
import br.com.capputeeno.auth.domain.entites.UserEntity;

public interface ITokenUseCase {

    SingInUserResponseDTO generate(UserEntity user);

    boolean validate(String token);

}
