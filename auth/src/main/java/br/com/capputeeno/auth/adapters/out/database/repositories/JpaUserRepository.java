package br.com.capputeeno.auth.adapters.out.database.repositories;

import br.com.capputeeno.auth.adapters.out.database.entities.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID> {
    Optional<JpaUserEntity> findByEmail(String email);

    Optional<JpaUserEntity> findByEmailOrUsername(String email, String username);
}
