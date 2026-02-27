package br.com.capputeeno.auth.adapters.out.database.repositories.mappers;

import br.com.capputeeno.auth.adapters.out.database.entities.JpaUserEntity;
import br.com.capputeeno.auth.domain.entites.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends Mapper<UserEntity, JpaUserEntity> {

    @Override
    public UserEntity toDomain(JpaUserEntity jpaUserEntity) {

        return new UserEntity(
                jpaUserEntity.getUserId(),
                jpaUserEntity.getUsername(),
                jpaUserEntity.getEmail(),
                jpaUserEntity.getPassword(),
                jpaUserEntity.getImageUrl()
        );
    }

    @Override
    public JpaUserEntity toInfra(UserEntity userEntity) {
        return new JpaUserEntity(
                userEntity
        );
    }

}
