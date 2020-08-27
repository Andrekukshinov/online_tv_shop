package application.andrei.kukshinov.entitiy;


import application.andrei.kukshinov.additionalEnumsAndClasses.PayType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "order_date")
    private Date creationDate ;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;
    @Column(name = "pay_type")
    private String payType;
    @Column(name = "total_price")
    private int totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private Set<JoinOrderTv> orderTvs = new TreeSet<>();

    public Order() {
    }


    public Order(long id, Date creationDate,
                 Date deliveryDate, Location location,
                 PayType payType, int totalPrice,
                 User user, Set<JoinOrderTv> orderTvs) {
        this.id = id;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.location = location;

        this.payType = payType.toString();
        this.totalPrice = totalPrice;
        this.user = user;
        this.orderTvs = orderTvs;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPayType() {
        return payType.toString();
    }

    public void setPayType(PayType payType) {
        this.payType = payType.toString();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<JoinOrderTv> getOrderTvs() {
        return orderTvs;
    }

    public void setOrderTvs(Set<JoinOrderTv> orderTvs) {
        this.orderTvs = orderTvs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
