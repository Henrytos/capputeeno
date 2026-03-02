package br.com.capputeeno.auth.application.port.in;

import br.com.capputeeno.auth.application.port.dtos.SignInUserRequestDTO;
import br.com.capputeeno.auth.application.port.dtos.SignInUserResponseDTO;

public interface IAuthenticationUseCase {

    SignInUserResponseDTO signInUser(SignInUserRequestDTO data);

}
