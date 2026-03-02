package br.com.capputeeno.auth.application.services;

import br.com.capputeeno.auth.application.port.out.IPasswordEncoder;
import br.com.capputeeno.auth.application.port.out.ITotpUseCase;
import br.com.capputeeno.auth.application.services.exceptions.UserAlreadyExistsException;
import br.com.capputeeno.auth.application.services.exceptions.WrongCredentialsException;
import br.com.capputeeno.auth.domain.entites.CreateUserRequestDTO;
import org.springframework.stereotype.Service;

import br.com.capputeeno.auth.application.port.in.IUserUseCase;
import br.com.capputeeno.auth.application.port.out.IUserRepository;
import br.com.capputeeno.auth.domain.entites.UserEntity;

@Service
public class UserUseCaseImpl implements IUserUseCase {

    private final IUserRepository iUserRepository;

    private final IPasswordEncoder iPasswordEncoder;

    private final ITotpUseCase iTotpUseCase;

    public UserUseCaseImpl(IUserRepository iUserRepository, IPasswordEncoder iPasswordEncoder, ITotpUseCase iTotpUseCase) {
        this.iUserRepository = iUserRepository;
        this.iPasswordEncoder = iPasswordEncoder;
        this.iTotpUseCase = iTotpUseCase;
    }


    @Override
    public UserEntity createUser(CreateUserRequestDTO data) {
        boolean userExists = this.iUserRepository.findByEmailOrUsername(data.email(), data.username()).isPresent();

        if (userExists) {
            throw new UserAlreadyExistsException("User with email " + data.email() + " ou username " + data.username() + " already exists");
        }

        UserEntity newUser = new UserEntity(data);

        String passwordEncode = this.iPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(passwordEncode);

        return this.iUserRepository.save(newUser);
    }

    @Override
    public void activateA2f(UserEntity user) {
        if(user.isActiveA2f())
            throw new WrongCredentialsException();

        String secret = this.iTotpUseCase.generateSecret(user);

        user.activateA2f();
        user.setSecretA2f(secret);

        this.iUserRepository.save(user);
    }

}
