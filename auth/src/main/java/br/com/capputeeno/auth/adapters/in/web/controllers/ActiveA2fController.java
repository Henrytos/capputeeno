package br.com.capputeeno.auth.adapters.in.web.controllers;

import br.com.capputeeno.auth.application.port.in.IUserUseCase;
import br.com.capputeeno.auth.application.port.out.ITotpUseCase;
import br.com.capputeeno.auth.domain.entites.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/a2f")
@RequiredArgsConstructor
@Slf4j
public class ActiveA2fController {

    private final IUserUseCase iUserUseCase;

    private final ITotpUseCase iTotpUseCase;

    @GetMapping
    public ResponseEntity<String> generateUrl(
            @AuthenticationPrincipal UserEntity user
    ){
        String url = this.iTotpUseCase.generateUrl(user);

        return ResponseEntity.ok(url);
    }

    @PostMapping("/active")
    public ResponseEntity<Void> active(
            @AuthenticationPrincipal UserEntity user
            ){
        this.iUserUseCase.activateA2f(user);

        return ResponseEntity.noContent().build();
    }

}
