package br.com.capputeeno.auth.application.port.out;

import br.com.capputeeno.auth.domain.entites.UserEntity;
import org.apache.catalina.User;

public interface ITotpUseCase {

    String generateSecret(UserEntity user);

    boolean verifyCode(String code, UserEntity user);

    String generateUrl(UserEntity user);
}
