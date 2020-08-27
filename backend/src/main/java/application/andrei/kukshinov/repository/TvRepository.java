package application.andrei.kukshinov.repository;

import application.andrei.kukshinov.entitiy.TvSet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TvRepository extends CrudRepository<TvSet, Long> {
    @Modifying
    @Transactional
    @Query("SELECT tv FROM TvSet tv WHERE tv.id IN :tvId")
    List<TvSet> findTvsInOrder(@Param("tvId")Iterable<Long> tvId);

    @Modifying
    @Transactional
    @Query("UPDATE TvSet tv SET tv = :tvData WHERE tv.id = :id")
    TvSet updateTvData(@Param("tvData") TvSet tv,@Param("id")long id);
}
