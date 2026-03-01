package br.com.capputeeno.auth.application.port.in;

import br.com.capputeeno.auth.application.port.dtos.SignInUserRequestDTO;
import br.com.capputeeno.auth.application.port.dtos.SingInUserResponseDTO;

public interface IAuthenticationUseCase {

    SingInUserResponseDTO signInUser(SignInUserRequestDTO data);

}
