package br.com.capputeeno.auth.application.port.out;

import java.util.Optional;

import br.com.capputeeno.auth.domain.entites.UserEntity;

public interface IUserRepository {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    UserEntity save(UserEntity user);

}
