package application.andrei.kukshinov.repository;

import application.andrei.kukshinov.entitiy.JoinOrderTv;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface JoinOrderTvRepository extends CrudRepository<JoinOrderTv, Long> {
    @Modifying
    @Transactional
    @Query("SELECT orders  FROM JoinOrderTv orders WHERE orders.order.creationDate BETWEEN :monthAgo AND :now")//
    List<JoinOrderTv> getAllTvAfter(@Param("monthAgo") Date from, @Param("now") Date till);
}
