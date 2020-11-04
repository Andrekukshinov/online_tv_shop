package application.andrei.kukshinov.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.JwtException;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static application.andrei.kukshinov.security.jwt.SecurityConstants.AUTHORIZATION_HEADER;
import static application.andrei.kukshinov.security.jwt.SecurityConstants.TOKEN_PREFIX;

@Setter
public class JwtValidationTokenFilter extends OncePerRequestFilter {
    private JwtProvider jwtProvider;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (Strings.isNullOrEmpty(authorization) || !authorization.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String token = authorization.replace(TOKEN_PREFIX, "");
        try{
            Authentication authentication = jwtProvider.getAuthentication(token, userDetailsService);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException | AuthenticationException exception) {
            throw new IllegalStateException("Token " + token + " cannot be trusted");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
