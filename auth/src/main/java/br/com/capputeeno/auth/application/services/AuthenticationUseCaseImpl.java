package br.com.capputeeno.auth.application.services;

import br.com.capputeeno.auth.application.port.in.IAuthenticationUseCase;
import br.com.capputeeno.auth.application.port.dtos.SignInUserRequestDTO;
import br.com.capputeeno.auth.application.port.dtos.SingInUserResponseDTO;
import br.com.capputeeno.auth.application.port.out.IPasswordEncoder;
import br.com.capputeeno.auth.application.port.out.ITokenUseCase;
import br.com.capputeeno.auth.application.port.out.IUserRepository;
import br.com.capputeeno.auth.application.services.exceptions.UserNotFoundException;
import br.com.capputeeno.auth.application.services.exceptions.WrongCredentialsException;
import br.com.capputeeno.auth.domain.entites.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCaseImpl implements IAuthenticationUseCase {

    private final IUserRepository iUserRepository;

    private final IPasswordEncoder iPasswordEncoder;

    private final ITokenUseCase iTokenUseCase;

    @Override
    public SingInUserResponseDTO signInUser(SignInUserRequestDTO data) {

        UserEntity user = this.iUserRepository.findByEmail(data.email()).orElseThrow(UserNotFoundException::new);

        boolean isPasswordMatched = iPasswordEncoder.matches(data.password(),user.getPassword());

        if(!isPasswordMatched){
            throw new WrongCredentialsException();
        }

        return iTokenUseCase.generate(user);
    }
}
