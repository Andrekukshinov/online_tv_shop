package application.andrei.kukshinov.repository;

import application.andrei.kukshinov.entitiy.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE  User user SET user.password =:password WHERE user.id = :id " )
    User updatePassword(@Param("password") String password, @Param("id") long id);

    Optional<User> findByUsername(String username);
}
