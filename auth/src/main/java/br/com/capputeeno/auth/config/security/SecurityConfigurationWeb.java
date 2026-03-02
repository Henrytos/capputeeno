package br.com.capputeeno.auth.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationWeb {

    public static final String[] ROUTES_NOT_AUTHENTICATED = {
            "/api/v1/auth/sign-in",
            "/api/v1/auth/sign-in/a2f",
            "/api/v1/users"
    };


    private final String[] ROUTES_AUTHENTICATED = {
            "/api/v1/auth/a2f",
            "/api/v1/auth/a2f/active"
    };

    @Autowired
    private TokenFilterChain tokenFilterChain;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests -> {
                            authorizeRequests
                                    .requestMatchers(ROUTES_NOT_AUTHENTICATED).permitAll()
                                    .requestMatchers(ROUTES_AUTHENTICATED).authenticated()
                                    .anyRequest().authenticated();
                        }
                ).addFilterBefore(tokenFilterChain, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
