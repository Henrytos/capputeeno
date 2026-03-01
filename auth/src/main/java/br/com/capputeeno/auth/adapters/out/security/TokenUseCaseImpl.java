package br.com.capputeeno.auth.adapters.out.security;

import br.com.capputeeno.auth.application.port.dtos.SingInUserResponseDTO;
import br.com.capputeeno.auth.application.port.out.ITokenUseCase;
import br.com.capputeeno.auth.domain.entites.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenUseCaseImpl implements ITokenUseCase {
    @Override
    public SingInUserResponseDTO generate(UserEntity user) {
        return null;
    }

    @Override
    public boolean validate(String token) {
        return false;
    }
}
