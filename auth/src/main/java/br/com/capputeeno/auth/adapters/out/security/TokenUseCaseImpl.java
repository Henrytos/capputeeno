package br.com.capputeeno.auth.adapters.out.security;

import br.com.capputeeno.auth.application.port.dtos.SignInUserResponseDTO;
import br.com.capputeeno.auth.application.port.out.ITokenUseCase;
import br.com.capputeeno.auth.domain.entites.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenUseCaseImpl implements ITokenUseCase {

    private final String JWT_SECRET_KEY;

    private final String JWT_ISSUER;

    public TokenUseCaseImpl(
            @Value("${auth.jwt-secret-key}") String JWT_SECRET_KEY, @Value("${auth.jwt-issuer}") String JWT_ISSUER) {
        this.JWT_SECRET_KEY = JWT_SECRET_KEY;
        this.JWT_ISSUER = JWT_ISSUER;
    }


    @Override
    public SignInUserResponseDTO generate(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
            String token = JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpireAt(20))
                    .sign(algorithm);

            String refreshToken = JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withExpiresAt(generateExpireAt(60 * 3)) // 3 hours
                    .sign(algorithm);

            return new SignInUserResponseDTO(token, refreshToken);
        } catch (JWTCreationException exception) {
            return null;
        }
    }

    @Override
    public boolean validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();

            verifier.verify(token);

            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    @Override
    public String getSubject(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();

            decodedJWT = verifier.verify(token);

            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpireAt(Integer incrementInMinutes) {
        return Instant.now().plus(Duration.ofMinutes(incrementInMinutes));
    }
}
