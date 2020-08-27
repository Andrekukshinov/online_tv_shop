package application.andrei.kukshinov.entitiy;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tv")
public class TvSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String producer;
    private String model;
    @Column(name = "img_URL")
    private String imgURl;
    private float diagonal;
    private String resolution;
    private boolean is3d = false;
    private String wifi;
    private String bluetooth;

    @OneToMany(mappedBy = "tv", cascade = {CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
//    @JsonIgnore
//    @JsonBackReference
    @JsonManagedReference
    private List<JoinOrderTv> orderTv;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tvModel")
//    @JsonIgnore
//    @JsonBackReference
    @JsonManagedReference
    private WarehouseOfTvs warehouse;

    public TvSet() {
    }

    public TvSet(long id, String producer, String model, String imgURl, float diagonal, String resolution, boolean is3d, String wifi, String bluetooth, List<JoinOrderTv> orderTv, WarehouseOfTvs warehouse) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.imgURl = imgURl;
        this.diagonal = diagonal;
        this.resolution = resolution;
        this.is3d = is3d;
        this.wifi = wifi;
        this.bluetooth = bluetooth;

        this.orderTv = orderTv;
        this.warehouse = warehouse;
    }

    public long getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImgURl() {
        return imgURl;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }

    public float getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(float diagonal) {
        this.diagonal = diagonal;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean getIs3d() {
        return is3d;
    }

    public void setIs3d(boolean is3d) {
        this.is3d = is3d;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public List<JoinOrderTv> getOrderTvs() {
        return orderTv;
    }

    public void setOrderTvs(List<JoinOrderTv> orderTvs) {
        this.orderTv = orderTvs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isIs3d() {
        return is3d;
    }

    public WarehouseOfTvs getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseOfTvs warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "TvSet{ model:" + producer + " " + model + " " + '}';
    }
}
