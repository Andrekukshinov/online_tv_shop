package application.andrei.kukshinov.entitiy;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    //
    private Location location;
    @Column(name = "pay_type")
    private String payType;
    @Column(name = "total_price")
    private int totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    //
    private User user;
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})

   //@JsonView(Views.Internal.class)
    private Set<JoinOrderTv> orderTvs = new TreeSet<>();

}
