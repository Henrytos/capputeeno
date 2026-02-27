package br.com.capputeeno.auth.adapters.out.database.entities;

import br.com.capputeeno.auth.domain.entites.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties( value = {
        "password"
})
public class JpaUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String username;
    private String email;
    private String password;

    @Column(name = "image_url")
    private String imageUrl;

    public JpaUserEntity(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.imageUrl = userEntity.getImageUrl();
    }
}
