package application.andrei.kukshinov.controller;


import application.andrei.kukshinov.entitiy.TvSet;
import application.andrei.kukshinov.entitiy.WarehouseOfTvs;
import application.andrei.kukshinov.repository.TvRepository;
import application.andrei.kukshinov.repository.WarehouseOfTvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

//todo deal with objects creation order
@RestController
@RequestMapping(value = "/tvs")
public class TvController {
    @Autowired
    private TvRepository tvRepository;
    @Autowired
    private WarehouseOfTvsRepository warehouseRepository;

    public TvController() {
    }

    public TvController(TvRepository tvRepository, WarehouseOfTvsRepository warehouseRepository) {
        this.tvRepository = tvRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public TvRepository getTvRepository() {
        return tvRepository;
    }

    public void setTvRepository(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    public WarehouseOfTvsRepository getWarehouseRepository() {
        return warehouseRepository;
    }

    public void setWarehouseRepository(WarehouseOfTvsRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }


    /**
     * The method returns all tvs from warehouse which status is active
     * @return List<TvSet>
     */
    @GetMapping("active")
    public Set<TvSet> getAllAvailableTvs() {
        Iterable<WarehouseOfTvs> warehouse = warehouseRepository.findAllByActive(true);
        Set<TvSet> tvSets = new LinkedHashSet<>();
        warehouse.forEach(tvAm -> {
            System.out.println();
                tvSets.add(tvAm.getTvModel());
        });
        return tvSets;
    }

    /**
     * Inverts activity status of tv
     * @param tv to be changed
     * @return  changed Tv
     */
    @PutMapping("/tv/switch")
    public TvSet changeIsActiveTv(@RequestBody TvSet tv){
        Optional<WarehouseOfTvs> ifWarehouse = warehouseRepository.findById(tv.getId());
        return ifWarehouse
                .map(warehouseOfTvs ->
                          warehouseRepository.changeTvActivity(!warehouseOfTvs.isActive(),tv.getId())
                                             .getTvModel()
                ).orElse(null);
    }

    /**
     * Saves a new tv to db
     * @param tv to be saved
     * @return saved tv
     */
    @PostMapping("/tv")
    public TvSet saveNewTv(@RequestBody TvSet tv){
        return tvRepository.save(tv);
    }

    /**
     * Deletes tv that is passed to method parameters from db
     * @param tv
     */
    @DeleteMapping("dlt/tv")
    public void deleteTv(@RequestBody TvSet tv) {
        warehouseRepository.deleteByTvModel_Id(tv.getId());
        tvRepository.delete(tv);
    }

    /**
     * Updates tv info in db
     * @param tv to be updated
     * @return updated tv
     */
    @PutMapping("tv/update")
    public TvSet updateTvData(@RequestBody TvSet tv) {
        return tvRepository.updateTvData(tv, tv.getId());
    }
}

