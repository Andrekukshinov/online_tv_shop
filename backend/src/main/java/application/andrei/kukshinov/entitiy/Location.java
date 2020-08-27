package application.andrei.kukshinov.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;
import javax.persistence.*;
//import java.util.Date;


@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    private String street;
    private String house;
    @Column(name = "flat_number")
    private String flatNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
// @JsonIgnore
//    @JsonManagedReference
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "location", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JsonIgnore
//    @JsonBackReference
    @JsonManagedReference
    private List<Order> orders;
    public Location() {
    }

    public Location(long id, String city, String street, String house, String flatNumber, User user, List<Order> orders) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flatNumber = flatNumber;
        this.user = user;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
