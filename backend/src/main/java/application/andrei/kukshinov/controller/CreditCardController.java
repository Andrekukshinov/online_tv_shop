package application.andrei.kukshinov.controller;


import application.andrei.kukshinov.entitiy.CreditCard;
import application.andrei.kukshinov.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;


//todo deal with objects creation order
@RestController
@RequestMapping("/cards")
public class CreditCardController {
    private CreditCardRepository cardRepository;

    public CreditCardController() {
    }

    public CreditCardController(CreditCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CreditCardRepository getCardRepository() {
        return cardRepository;
    }

    @Autowired
    public void setCardRepository(CreditCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping
    public CreditCard saveCreditCard(CreditCard card) {
        return cardRepository.save(card);
    }

    @GetMapping("/user/card")
    public Set<CreditCard> getUserCreditCards(CreditCard card) {
        
        return cardRepository.findCreditCardByUser_Id(card.getUser().getId()).orElseGet(TreeSet::new);
    }

    @DeleteMapping("/user/card")
    public int deleteUserCreditCard(@RequestBody CreditCard card) {
        return cardRepository.deleteCreditCardById(card.getUser().getId(), card.getId());
    }

    @DeleteMapping("/user/cards")
    public int deleteAllUsersCards(@RequestBody CreditCard card) {
        return cardRepository.deleteCreditCardsByUser_Id(card.getUser().getId());
    }
   //todo think of possible response entities usages as returning value from methods
}

