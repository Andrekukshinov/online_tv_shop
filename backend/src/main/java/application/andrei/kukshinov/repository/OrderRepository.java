package application.andrei.kukshinov.repository;

import application.andrei.kukshinov.entitiy.Order;
import application.andrei.kukshinov.entitiy.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> getAllByCreationDateAfter (Date date);

    List<Order> getAllByUser_Id(long id);
}
