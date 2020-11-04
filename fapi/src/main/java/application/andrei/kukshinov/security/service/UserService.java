package application.andrei.kukshinov.security.service;

import application.andrei.kukshinov.security.User;

import java.util.List;

public interface UserService {
    User saveNewUser(User user);
    List<User> findAllUsers();
}
