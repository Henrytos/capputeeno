package br.com.capputeeno.auth.adapters.in.web.controllers;

import br.com.capputeeno.auth.adapters.in.web.controllers.presenters.CreateUserPresenter;
import br.com.capputeeno.auth.application.port.in.IUserUseCase;
import br.com.capputeeno.auth.domain.entites.CreateUserRequestDTO;
import br.com.capputeeno.auth.domain.entites.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class CreateUserController {

    private final IUserUseCase iUserUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserPresenter> create(
            @RequestBody CreateUserRequestDTO request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        UserEntity user = this.iUserUseCase.createUser(request);

        URI uri = uriComponentsBuilder.path("/api/v1/users/{id}").buildAndExpand(user.getUserId()).toUri();

        return ResponseEntity.created(uri).body(new CreateUserPresenter(user));
    }
}
