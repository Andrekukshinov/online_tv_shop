package application.andrei.kukshinov.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static application.andrei.kukshinov.security.jwt.SecurityConstants.*;
import static java.lang.System.currentTimeMillis;

@Component
public class JwtProvider {
    // TODO: 13.09.2020 create comments fo the rest methods
    private String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Method extracts claims from received token (in final jwt string form(jws)) and gets authorities from claims
     * and pares received authorities to a map of simpleGrantedAuthorities. Than these authorities are passed to
     * an authorization instance as well as username.
     *
     * @param token - jwt token that is passed ti the method, used to get claims.
     * @param tokenUsername is used to create authentication object.
     * @return UsernamePasswordAuthenticationToken that implements authorization interface.
     */
    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token, String tokenUsername) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        var authorities = (List<Map<String, String>>) body.get(CLAIM_AUTHORITIES);
        Set<SimpleGrantedAuthority> userAuthorities = authorities
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                .collect(Collectors.toSet());
        return new UsernamePasswordAuthenticationToken(tokenUsername, null, userAuthorities);
    }

    /**Returns specific token claim, based on claimsParser(Function) impl that is passed.
     * @param token String
     * @param claimsParser Function<Claims, T>
     * @param <T> generic type returned from function
     * @return
     */
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsParser) {
        Claims tokenClaims = getAllTokenClaims(token);
        return claimsParser.apply(tokenClaims);
    }

    /**
     * Extracts all claims from retrieved token and returns as Claims class
     * @param token String
     * @return Claims from token body
     */
    private Claims getAllTokenClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parseClaimsJws(token).getBody();
    }

    /**Checks wether token expiration is after current date.
     * @param token String
     * @return comparison results.
     */
    private boolean isTokenActive(String token) {
        return getClaimFromToken(token, Claims::getExpiration).after(new Date());
    }

    /**Extracts username from token and uses that for uploading user details info from user details service.
     * These details are used to compare username from db with token username and than checks if token is not expired.
     * @param token
     * @param userDetailsService
     * @returns result comparison results.
     */
    private boolean validateToken(String token, UserDetailsService userDetailsService) {
        String username = getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return (username.equals(userDetails.getUsername())) && isTokenActive(token);

    }

    /**Generates jwt token based on authentication username and authorities. And signs it using secret word.
     * @param authentication Authentication
     * @return jwt token (String)
     */
    public String generateToken(Authentication authentication){
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(CLAIM_AUTHORITIES, authentication.getAuthorities())
                .setIssuedAt(new Date()).setExpiration(new Date(currentTimeMillis() + (EXPIRATION_TOKEN_TIME_SECONDS * 1000)))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public Authentication getAuthentication(String token, UserDetailsService userDetailsService) throws AuthenticationException {
        String tokenUsername = getUsernameFromToken(token);
        if (validateToken(token, userDetailsService) && !Strings.isNullOrEmpty(tokenUsername)) {
            return getUsernamePasswordAuthenticationToken(token, tokenUsername);
        }
        throw new AuthenticationException("Authentication failed");
    }


}
