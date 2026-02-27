package br.com.capputeeno.auth.application.port.in;

import br.com.capputeeno.auth.domain.entites.CreateUserRequestDTO;
import br.com.capputeeno.auth.domain.entites.UserEntity;

public interface IUserUseCase {
    UserEntity createUser(CreateUserRequestDTO entity);
}
