//package application.andrei.kukshinov.additionalEnumsAndClasses;
//
//import application.andrei.kukshinov.entitiy.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EncodePassUtil {
//    private PasswordEncoder encoder;
//
//    @Autowired
//    public EncodePassUtil(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }
//    public User encode(User user) {
//        user.setPassword(encoder.encode(user.getPassword()));
//        return user;
//    }
//}
