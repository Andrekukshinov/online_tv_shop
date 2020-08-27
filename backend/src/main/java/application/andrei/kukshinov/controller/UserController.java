package application.andrei.kukshinov.controller;

//import application.andrei.kukshinov.additionalEnumsAndClasses.EncodePassUtil;
import application.andrei.kukshinov.additionalEnumsAndClasses.NoEntityExeption;
import application.andrei.kukshinov.entitiy.CreditCard;
import application.andrei.kukshinov.entitiy.User;
import application.andrei.kukshinov.repository.CreditCardRepository;
import application.andrei.kukshinov.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;

//todo deal with objects creation order
@RestController
@Data
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final CreditCardRepository cardRepository;
//    private EncodePassUtil passwordEncoder;
    @Autowired
    public UserController(UserRepository userRepository, CreditCardRepository cardRepository/*, EncodePassUtil passwordEncoder*/) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
//        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/user/card")
    public int saveUsersCards(User user) {
        Set<CreditCard> userCards = new TreeSet<>();
        user.getCreditCards().forEach(card -> userCards.add(cardRepository.save(card)));
        return userCards.size();
    }

    @PostMapping
    public User saveNewUser(@RequestBody User user) {
        return userRepository.save(/*passwordEncoder.encode(*/user/*)*/);
    }
    @PostMapping("/upd/password")
    public User updatePassword(@RequestBody User user) {
       return userRepository.updatePassword(/*passwordEncoder.encode(*/user/*)*/.getPassword(), user.getId());
    }
    @GetMapping("/{id}")
    public User getUserById( @PathVariable String id) {
        long myId = Long.parseLong(id);
        return userRepository.findById(myId).orElseThrow(()-> new NoEntityExeption(myId));
    }
}



