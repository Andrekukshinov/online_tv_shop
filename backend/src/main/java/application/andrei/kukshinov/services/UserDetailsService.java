//package application.andrei.kukshinov.services;
//
//import application.andrei.kukshinov.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//    @Autowired
//    private UserRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user " + username + " was not found"));
//    }
//}
