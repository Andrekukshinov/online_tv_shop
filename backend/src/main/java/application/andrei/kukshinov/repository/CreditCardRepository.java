package application.andrei.kukshinov.repository;

import application.andrei.kukshinov.entitiy.CreditCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
    Optional<Set<CreditCard>> findCreditCardByUser_Id(long userId) ;

    @Modifying
    @Transactional
    @Query("DELETE FROM CreditCard card WHERE card.user.id = :userId AND card.id = :cardId")
    int deleteCreditCardById(@Param("userId")long userId, @Param("cardId") long id);

    int deleteCreditCardsByUser_Id(long userId);

}
