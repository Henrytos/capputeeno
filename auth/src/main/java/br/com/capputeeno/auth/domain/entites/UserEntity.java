package br.com.capputeeno.auth.domain.entites;

import java.util.UUID;

import br.com.capputeeno.auth.adapters.out.database.entities.JpaUserEntity;
import br.com.capputeeno.auth.domain.exceptions.InvalidInputException;

public class UserEntity {

    private UUID userId;
    private String username;
    private String email;
    private String password;
    private String imageUrl;

    public UserEntity() {
    }

    public UserEntity(CreateUserRequestDTO data) {
        this(
                data.username(),
                data.email(),
                data.password(),
                data.imageUrl()
        );
    }

    public UserEntity(String username, String email, String password, String imageUrl) {

        if (username.isBlank() || email.isBlank() || password.isBlank() || imageUrl.isBlank()) {
            throw new InvalidInputException();
        }

        this.username = username;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public UserEntity(UUID userId, String username, String email, String password, String imageUrl) {

        if (userId == null || username.isBlank() || email.isBlank() || password.isBlank() || imageUrl.isBlank()) {
            throw new InvalidInputException();
        }

        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
