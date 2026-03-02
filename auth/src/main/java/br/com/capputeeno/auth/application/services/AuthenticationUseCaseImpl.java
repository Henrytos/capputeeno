package br.com.capputeeno.auth.application.services;

import br.com.capputeeno.auth.application.port.in.IAuthenticationUseCase;
import br.com.capputeeno.auth.application.port.dtos.SignInUserRequestDTO;
import br.com.capputeeno.auth.application.port.dtos.SignInUserResponseDTO;
import br.com.capputeeno.auth.application.port.out.IPasswordEncoder;
import br.com.capputeeno.auth.application.port.out.ITokenUseCase;
import br.com.capputeeno.auth.application.port.out.ITotpUseCase;
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

    private final ITotpUseCase iTotpUseCase;

    @Override
    public SignInUserResponseDTO signInUser(SignInUserRequestDTO data) {

        UserEntity user = this.iUserRepository.findByEmail(data.email()).orElseThrow(UserNotFoundException::new);

        boolean isPasswordMatched = iPasswordEncoder.matches(data.password(),user.getPassword());

        if(!isPasswordMatched){
            throw new WrongCredentialsException();
        }

        if(user.isActiveA2f()){
            return  new SignInUserResponseDTO(null, null, true);
        }

        String token = iTokenUseCase.generate(user);
        String refreshToken = iTokenUseCase.generate(user);

        return new SignInUserResponseDTO(token, refreshToken, false);
    }

    @Override
    public SignInUserResponseDTO signInUser(String email, String code) {
        UserEntity user = this.iUserRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        if(!user.isActiveA2f())
            throw new WrongCredentialsException();

        boolean isCodeValid = iTotpUseCase.verifyCode(code, user);

        if(!isCodeValid)
            throw new WrongCredentialsException();


        String token = iTokenUseCase.generate(user);
        String refreshToken = iTokenUseCase.generate(user);

        return new SignInUserResponseDTO(token, refreshToken, true);
    }
}
