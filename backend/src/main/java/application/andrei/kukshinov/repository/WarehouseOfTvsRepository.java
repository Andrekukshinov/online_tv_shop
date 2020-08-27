package application.andrei.kukshinov.repository;

import application.andrei.kukshinov.entitiy.TvSet;
import application.andrei.kukshinov.entitiy.WarehouseOfTvs;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public interface WarehouseOfTvsRepository extends CrudRepository<WarehouseOfTvs, Long> {
    @Modifying
    @Transactional
    @Query("SELECT warehouse FROM WarehouseOfTvs warehouse WHERE warehouse.tvModel.id IN :tvId")
    List<WarehouseOfTvs> findTvsInOrder(@Param("tvId")Iterable<Long> tvId);
    @Modifying
    @Transactional
    @Query("SELECT warehouse FROM WarehouseOfTvs warehouse WHERE warehouse.isActive = :isActive")
    List<WarehouseOfTvs> findAllByActive(@Param("isActive") boolean isActive);

    @Modifying
    @Transactional
    @Query("UPDATE WarehouseOfTvs tv SET tv.tvAmount = (tv.tvAmount - :quantity) WHERE tv.id = :tvId")
    int updateTvAmountInWarehouse(@Param("quantity") int amount, @Param("tvId") long tvId);

    @Modifying
    @Transactional
    @Query("UPDATE WarehouseOfTvs warehouse SET warehouse.isActive = :change WHERE warehouse.id = :id")
    WarehouseOfTvs changeTvActivity(@Param("change") boolean switchActivity, @Param("id") long id);

//    @Transactional
//    @Modifying
//    @Query("SELECT wh FROM WarehouseOfTvs wh WHERE wh.tvModel.id IN")
//    List<WarehouseOfTvs> tvsInWareHouse(@Param("tvId")List<Long> ids);

    void deleteByTvModel_Id(long tvId);
}
