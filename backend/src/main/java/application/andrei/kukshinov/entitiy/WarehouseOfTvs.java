package application.andrei.kukshinov.entitiy;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "warehouse_tv_amounts")
public class WarehouseOfTvs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "amount")
    private int tvAmount;
    private float price;
    private boolean isActive;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_id")
//    @JsonIgnore
//    @JsonManagedReference
    @JsonBackReference
    private TvSet tvModel;

    public WarehouseOfTvs() {
    }

    public WarehouseOfTvs(long id, int tvAmount, float price, boolean isActive, TvSet tvModel) {
        this.id = id;
        this.tvAmount = tvAmount;
        this.price = price;
        this.isActive = isActive;
        this.tvModel = tvModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTvAmount() {
        return tvAmount;
    }

    public void setTvAmount(int tvAmount) {
        this.tvAmount = tvAmount;
    }

    public TvSet getTvModel() {
        return tvModel;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setTvModel(TvSet tvModel) {
        this.tvModel = tvModel;
    }

}
