package br.com.capputeeno.auth.adapters.out.database.repositories;

import br.com.capputeeno.auth.adapters.out.database.entities.JpaUserEntity;
import br.com.capputeeno.auth.adapters.out.database.repositories.mappers.UserMapper;
import br.com.capputeeno.auth.application.port.out.IUserRepository;
import br.com.capputeeno.auth.domain.entites.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final JpaUserRepository jpaUserRepository;

    private final UserMapper userMapper;

    @Override
    public Optional<UserEntity> findByEmail(String email) {

        Optional<JpaUserEntity> jpaUserEntity = this.jpaUserRepository.findByEmail(email);

        return jpaUserEntity.map(this.userMapper::toDomain);

    }

    @Override
    public Optional<UserEntity> findByEmailOrUsername(String email, String username) {
        Optional<JpaUserEntity> jpaUserEntity = this.jpaUserRepository.findByEmailOrUsername(email, username);

        return jpaUserEntity.map(this.userMapper::toDomain);
    }

    @Override
    public UserEntity save(UserEntity user) {
        JpaUserEntity jpaUserEntity = this.jpaUserRepository.save(this.userMapper.toInfra(user));
        return this.userMapper.toDomain(jpaUserEntity);
    }

}
