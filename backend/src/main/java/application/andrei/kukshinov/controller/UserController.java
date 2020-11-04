package application.andrei.kukshinov.controller;

//import application.andrei.kukshinov.additionalEnumsAndClasses.EncodePassUtil;

import application.andrei.kukshinov.additionalEnumsAndClasses.NoEntityExeption;
import application.andrei.kukshinov.entitiy.CreditCard;
import application.andrei.kukshinov.entitiy.User;
import application.andrei.kukshinov.repository.CreditCardRepository;
import application.andrei.kukshinov.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//todo deal with objects creation order
@RestController
@Getter
@Setter
@RequestMapping(path = "/users" )
public class UserController {

    private final UserRepository userRepository;
    private final CreditCardRepository cardRepository;
    @Autowired
    public UserController(UserRepository userRepository, CreditCardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @PostMapping("/user/card")
    public int saveUsersCards(@RequestBody User user) {
        Set<CreditCard> userCards = new TreeSet<>();
        user.getCreditCards().forEach(card -> userCards.add(cardRepository.save(card)));
        return userCards.size();
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    @PostMapping("/upd/password")
    public User updatePassword(@RequestBody User user) {
       return userRepository.updatePassword(user.getPassword(), user.getId());
    }
    @GetMapping("/id/{id}")
    public User getUserById( @PathVariable String id) {
        long myId = Long.parseLong(id);
        return userRepository.findById(myId).orElseThrow(()-> new NoEntityExeption(myId));
    }

    @GetMapping("/{username}")
    public User getUserByEmail(@PathVariable("username") String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NoEntityExeption(-1l));
    }
    @GetMapping("/all")
    public List<User> findAllUsers() {
        Iterable<User> usersFromDb = userRepository.findAll();
        return StreamSupport.stream(usersFromDb.spliterator(), false).collect(Collectors.toList());
    }
}



