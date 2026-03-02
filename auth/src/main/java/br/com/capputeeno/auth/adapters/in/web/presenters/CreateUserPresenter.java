package br.com.capputeeno.auth.adapters.in.web.presenters;

import br.com.capputeeno.auth.domain.entites.UserEntity;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateUserPresenter {

    private final UUID userId;
    private final String username;
    private final String email;
    private final String password;
    private final String imageUrl;

    public CreateUserPresenter(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.imageUrl = userEntity.getImageUrl();
    }
}
