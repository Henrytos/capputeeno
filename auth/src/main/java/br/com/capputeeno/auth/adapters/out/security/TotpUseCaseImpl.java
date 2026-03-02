package br.com.capputeeno.auth.adapters.out.security;

import br.com.capputeeno.auth.application.port.out.ITotpUseCase;
import br.com.capputeeno.auth.domain.entites.UserEntity;
import com.atlassian.onetime.core.TOTPGenerator;
import com.atlassian.onetime.model.TOTPSecret;
import com.atlassian.onetime.service.RandomSecretProvider;
import org.springframework.stereotype.Service;

@Service
public class TotpUseCaseImpl implements ITotpUseCase {

    private final static String ISSUER = "Capputeeno";

    @Override
    public String generateSecret(UserEntity user) {
        return new RandomSecretProvider().generateSecret().getBase32Encoded();
    }

    @Override
    public boolean verifyCode(String code, UserEntity user) {
        TOTPSecret secretDecoded = TOTPSecret.Companion.fromBase32EncodedString(user.getSecretA2f());
        String codeGenerated = new TOTPGenerator().generateCurrent(secretDecoded).getValue();

        return codeGenerated.equals(code);
    }

    @Override
    public String generateUrl(UserEntity user) {

        return """
                otpauth://totp/%s:%s?secret=%s&issuer=%s
                """.formatted(ISSUER, user.getUsername(), user.getSecretA2f(), ISSUER);
    }
}
