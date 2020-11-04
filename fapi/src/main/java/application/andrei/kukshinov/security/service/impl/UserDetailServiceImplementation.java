package application.andrei.kukshinov.security.service.impl;

import application.andrei.kukshinov.security.User;
import application.andrei.kukshinov.security.service.UserDetailsService;
import application.andrei.kukshinov.security.service.UserService;
import application.andrei.kukshinov.service.httpService.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("securityServiceImpl")
public class UserDetailServiceImplementation implements UserDetailsService, UserService {

    private RestService restService;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return restService.getUserDetailsByUsername(username);
    }

    @Override
    public User saveNewUser(User user) {
        return restService.saveNewUser(user, encoder);

    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> findAllUsers() {
       return restService.findAllUsers();
    }

    @Autowired
    public void setRestService(RestService restService) {
        this.restService = restService;
    }
}
