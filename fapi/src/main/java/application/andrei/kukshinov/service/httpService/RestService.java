package application.andrei.kukshinov.service.httpService;

import application.andrei.kukshinov.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RestService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private  RestTemplate restTemplate;

    private Set<? extends GrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority role = new SimpleGrantedAuthority( user.getAuthorities().toString());
        authorities.add(role);
        return authorities;
    }
    private User getByEmail(String email) {
        return restTemplate.getForObject(backendServerUrl + "/users/" + email, User.class);
    }
    public UserDetails getUserDetailsByUsername(String username) {
        User userIfFound = getByEmail(username);
        userIfFound.setUsername(username);
        return new org.springframework.security.core.userdetails.User(userIfFound.getUsername(), userIfFound.getPassword(), getAuthority(userIfFound));
    }
    public User saveNewUser(User user, PasswordEncoder encoder) {
        user.setPassword(encoder.encode(user.getPassword()));
        HttpEntity<User> request = new HttpEntity<>(user);
        return restTemplate.postForEntity(backendServerUrl + "users/save", request, User.class).getBody();
    }

    public List<User> findAllUsers() {
        User[] response = restTemplate.getForObject(backendServerUrl + "/users/all", User[].class);
        return response == null ? Collections.emptyList()  : Arrays.asList(response);
    }


}
