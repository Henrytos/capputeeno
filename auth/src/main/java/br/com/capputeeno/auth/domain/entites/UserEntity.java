package br.com.capputeeno.auth.domain.entites;

import java.util.UUID;

import br.com.capputeeno.auth.domain.exceptions.InvalidInputException;

public class UserEntity {

    private UUID userId;
    private String username;
    private String email;
    private String password;
    private String imageUrl;
    private String secretA2f;
    private boolean activeA2f = false;

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

    public UserEntity(UUID userId, String username, String email, String password, String imageUrl, String secretA2f,  boolean activeA2f) {

        if (userId == null || username.isBlank() || email.isBlank() || password.isBlank() || imageUrl.isBlank()) {
            throw new InvalidInputException();
        }

        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
        this.secretA2f = secretA2f;
        this.activeA2f = activeA2f;
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

    public boolean isActiveA2f() {
        return activeA2f;
    }

    public String getSecretA2f() {
        return secretA2f;
    }

    public void setSecretA2f(String secretA2f) {
        this.secretA2f = secretA2f;
    }

    public void setActiveA2f(boolean activeA2f) {
        this.activeA2f = activeA2f;
    }

    public void activateA2f() {
        this.activeA2f = true;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
