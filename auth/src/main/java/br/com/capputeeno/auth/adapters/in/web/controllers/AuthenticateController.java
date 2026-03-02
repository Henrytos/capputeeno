package br.com.capputeeno.auth.adapters.in.web.controllers;

import br.com.capputeeno.auth.application.port.dtos.SignInUserRequestDTO;
import br.com.capputeeno.auth.application.port.dtos.SignInUserResponseDTO;
import br.com.capputeeno.auth.application.port.in.IAuthenticationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final IAuthenticationUseCase iAuthenticationUseCase;

    @PostMapping("/sign-in")
    public ResponseEntity<SignInUserResponseDTO> sing(
            @RequestBody SignInUserRequestDTO request
    ) {
        SignInUserResponseDTO response = this.iAuthenticationUseCase.signInUser(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-in/a2f")
    public ResponseEntity<SignInUserResponseDTO> singA2f(
            @RequestBody SignInUserRequestDTO request,
            @RequestParam String code
    ) {
        SignInUserResponseDTO response = this.iAuthenticationUseCase.signInUser(request.email(), code);

        return ResponseEntity.ok(response);
    }

}
