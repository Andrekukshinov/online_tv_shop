package application.andrei.kukshinov.security.jwt;

import application.andrei.kukshinov.security.jwt.JwtProvider;
import application.andrei.kukshinov.security.UsernamePassModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.andrei.kukshinov.security.jwt.SecurityConstants.AUTHORIZATION_HEADER;
import static application.andrei.kukshinov.security.jwt.SecurityConstants.TOKEN_PREFIX;

public class JwtUserNamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider = new JwtProvider();

    public JwtUserNamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernamePassModel usernamePassModel = new ObjectMapper().readValue(request.getInputStream(), UsernamePassModel.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    usernamePassModel.getUsername(),
                    usernamePassModel.getPassword()
            );
            return authenticationManager.authenticate(authentication);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = jwtProvider.generateToken(authResult);
        response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
    }
}
