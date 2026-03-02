package br.com.capputeeno.auth.config.security;

import br.com.capputeeno.auth.adapters.out.database.entities.JpaUserEntity;
import br.com.capputeeno.auth.adapters.out.database.repositories.JpaUserRepository;
import br.com.capputeeno.auth.adapters.out.database.repositories.mappers.UserMapper;
import br.com.capputeeno.auth.application.port.out.ITokenUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class TokenFilterChain extends OncePerRequestFilter {

    @Autowired
    private ITokenUseCase iTokenUseCase;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        List<String> routesPermit = List.of(SecurityConfigurationWeb.ROUTES_NOT_AUTHENTICATED);

        if (header != null && !routesPermit.contains(request.getRequestURI())) {
            String token = header.replaceAll("Bearer ", "");

            boolean isValidToken = this.iTokenUseCase.validate(token);

            if (!isValidToken) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String email = this.iTokenUseCase.getSubject(token);

            Optional<JpaUserEntity> user = jpaUserRepository.findByEmail(email);

            if (user.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }


            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(this.userMapper.toDomain(user.get()), null, user.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            request.setAttribute("userId", user.get().getUserId());
        }

        filterChain.doFilter(request, response);
    }
}