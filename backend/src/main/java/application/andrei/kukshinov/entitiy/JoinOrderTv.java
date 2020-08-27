package application.andrei.kukshinov.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "orders_tv")
public class JoinOrderTv implements Comparable<JoinOrderTv>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    @JoinColumn(name = "order_id")
//    @JsonIgnore
//    @JsonManagedReference
    @JsonBackReference
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_id")
//    @JsonIgnore
//    @JsonManagedReference
    @JsonBackReference
    private TvSet tv;
    @Column(name = "tv_model_price")
    private int tvModelPrice;
    @Column(name = "tv_quantity")
    private byte tvQuantity;

    public JoinOrderTv() {
    }

    public JoinOrderTv(long id, Order orders, TvSet tv, int tvModelPrice, byte tvQuantity) {
        this.id = id;
        this.order = orders;
        this.tv = tv;
        this.tvModelPrice = tvModelPrice;
        this.tvQuantity = tvQuantity;
    }

    public long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orders) {
        this.order = orders;
    }

    public TvSet getTv() {
        return tv;
    }

    public void setTv(TvSet tvs) {
        this.tv = tvs;
    }

    public int getTvModelPrice() {
        return tvModelPrice;
    }

    public void setTvModelPrice(int tvModelPrice) {
        this.tvModelPrice = tvModelPrice;
    }

    public byte getTvQuantity() {
        return tvQuantity;
    }

    public void setTvQuantity(byte tvQuantity) {
        this.tvQuantity = tvQuantity;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(JoinOrderTv o) {
        if (this.id == o.id) return 0;
        else if (this.id > o.id) return 1;
        else return 0;
    }
}
