package application.andrei.kukshinov.controller;


import application.andrei.kukshinov.security.User;
import application.andrei.kukshinov.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/register")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("securityServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User saveNewUser(@RequestBody User user) {
        return userService.saveNewUser(user);
    }

    @GetMapping()
    public String gr(/*User user*/) {
        return "userService .saveNewUser(user);";
    }
}
